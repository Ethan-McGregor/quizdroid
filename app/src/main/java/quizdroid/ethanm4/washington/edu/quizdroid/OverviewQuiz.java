package quizdroid.ethanm4.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OverviewQuiz extends AppCompatActivity implements View.OnClickListener {
    private int count;
    private String dis;
    Quiz temp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_quiz);
      temp  = (Quiz) getIntent().getSerializableExtra("quiz");
        dis = temp.getDescription();
        count = temp.getQuestionCount();

        TextView questionCount = (TextView) findViewById(R.id.qCount);
        TextView discription = (TextView) findViewById(R.id.dis);
        Button begin = (Button) findViewById(R.id.btnBegin);
        questionCount.setText("Question Count: " + count);
        discription.setText(dis);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(OverviewQuiz.this, TakeQuiz.class);
        intent.putExtra("Quiz",temp);
        startActivity(intent);
    }
}
