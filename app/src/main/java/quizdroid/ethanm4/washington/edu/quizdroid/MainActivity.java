package quizdroid.ethanm4.washington.edu.quizdroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {
    protected QuizApp app;
    public List<Quiz> lists = new ArrayList<Quiz>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (QuizApp)getApplication();

        lists = app.getRepo();

        ListView topics = (ListView) findViewById(R.id.lin_view);

        for(int i = 0; i < lists.size(); i++){
            Log.v("HERE", i + "");
        }

        MyCustomAdapter adapter = new MyCustomAdapter();
        topics.setAdapter(adapter);
        AdapterView.OnItemClickListener topicClickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String topicName = parent.getItemAtPosition(position).toString();
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
}
