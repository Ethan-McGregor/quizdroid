package quizdroid.ethanm4.washington.edu.quizdroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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


            lists = new ArrayList<Quiz>();
//            try {
//                synchronized (this) {
//                    wait(1000);
//                }
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//
//                e.printStackTrace();
//            }

            app = (QuizApp) getApplication();
            Log.v("What was First?", "THIS");


            ListView topics = (ListView) findViewById(R.id.lin_view);

            for (int i = 0; i < lists.size(); i++) {
                Log.v("HERE", i + "");
            }



            lists = app.getRepo();

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

                //sets the views
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

            // Showing progress dialog

            pDialog = new ProgressDialog(MainActivity.this);

            pDialog.setMessage("Fetching topics...");

            pDialog.setCancelable(false);

            pDialog.show();

        }


        @Override
        protected Void doInBackground(Void... arg0) {
            // let repository fetch JSON topics in the background
            ((QuizApp) getApplication()).getRepository().initializeRepo(MainActivity.this);
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();



            // put parsed JSON into RecyclerView

            final List<Quiz> topics = ((QuizApp) getApplication()).getRepository().getQuizes();

            // instantiate RecyclerView
            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            mRecyclerView.setAdapter(new RecyclerView.Adapter<TopicViewHolder>() {

                @Override
                public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View v = LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.topic_row,
                            parent,
                            false);
                    return new TopicViewHolder(v);
                }


                @Override
                public void onBindViewHolder(TopicViewHolder vh, int position) {

                    // get TextViews and replace with the title and short description
                    TextView tv = (TextView) vh.itemView.findViewById(R.id.text1);
                    tv.setText(topics.get(position).getTitle());
                    tv.setCompoundDrawablesWithIntrinsicBounds(topics.get(position).getIcon(), 0, 0, 0);
                    tv.setCompoundDrawablePadding(12);
                    tv = (TextView) vh.itemView.findViewById(R.id.text2);
                    tv.setText(topics.get(position).getShortDesc());

                }


                @Override

                public int getItemCount() {

                    return topics.size();
                }
            });
        }
    }

    private class TopicViewHolder

            extends RecyclerView.ViewHolder

            implements View.OnClickListener {



        public TopicViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);

        }



        @Override

        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(), QuizActivity.class);

            intent.putExtra(EXTRA_TOPIC, getAdapterPosition());

            startActivity(intent);

        }

    }



    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_preferences:

                // go to preferences activity

                startActivity(new Intent(getApplicationContext(), PreferencesActivity.class));

                return true;





            default:

                // If we got here, the user's action was not recognized.

                // Invoke the superclass to handle it.

                return super.onOptionsItemSelected(item);



        }

    }

}