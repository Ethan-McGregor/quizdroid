package quizdroid.ethanm4.washington.edu.quizdroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    protected QuizApp app;
    public List<Quiz> lists = new ArrayList<Quiz>();
    private boolean displayed = false;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            new GetTopics().execute();
        app = (QuizApp) getApplication();
        isNetworkAvailable();

        //Creates popUp if user has airplaneMode on
        if(isAirplaneModeOn(this)){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(R.string.title)
                    .setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                    startActivity(intent);

                }
                })
           .setNegativeButton(R.string.nope, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                   }
               });
             builder.create().show();
           }
        }


    //lets user know if not connected to the internet on startup
    private void isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean on = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if(!on){
            Toast.makeText(this,"YOU ARE NOT CONNECTED TO THE INTERNET",Toast.LENGTH_LONG);
        }


    }
    private static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(),Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
    private class MyCustomAdapter extends BaseAdapter {

        private List<Quiz> topics = lists;
        private LayoutInflater mInflater;

        public MyCustomAdapter() {
            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return topics.size();
        }

        @Override
        public Object getItem(int position) {
            return topics.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            System.out.println("getView " + position + " " + convertView);
            if (convertView ==null) {
                convertView = mInflater.inflate(R.layout.layout_row, parent, false);
                ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
                TextView title = (TextView) convertView.findViewById(R.id.title);
                TextView description = (TextView) convertView.findViewById(R.id.dis);

                Quiz topic = (Quiz) getItem(position);
                title.setText(topic.getTitle());
                description.setText(topic.getDescription());

                image.setImageResource(topic.getIcon());
            }
            return convertView;
        }
    }
    class GetTopics extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {

            super.onPreExecute();

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Fetching topics...");
            pDialog.setCancelable(false);
            pDialog.show();

        }


        @Override
        protected Void doInBackground(Void... arg0) {

            ((QuizApp) getApplication()).getRepository().initializeRepo(MainActivity.this);
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();
            lists = null;
            lists = app.getRepo();

            ListView topics = (ListView) findViewById(R.id.lin_view);

            MyCustomAdapter adapter = new MyCustomAdapter();
            topics.setAdapter(adapter);
            displayed = true;

            AdapterView.OnItemClickListener topicClickListener = new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Quiz topic = lists.get(position);
                    Intent topicOverview = new Intent(MainActivity.this, OverviewQuiz.class);
                    topicOverview.putExtra("topic", topic);
                    if (topicOverview.resolveActivity(getPackageManager()) != null) {
                        startActivity(topicOverview);
                    }
                }
            };
            topics.setOnItemClickListener(topicClickListener);

        }
    }
}
