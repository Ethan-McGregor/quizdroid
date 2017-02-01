package quizdroid.ethanm4.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import java.io.Serializable;

public class Answer extends AppCompatActivity implements Serializable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        String a = getIntent().getStringExtra("answer");
        String c = getIntent().getStringExtra("cAnswer");
        TextView answer = (TextView) findViewById(R.id.answer);
        TextView canswer = (TextView) findViewById(R.id.correctAnswer);
        TextView score = (TextView) findViewById(R.id.score);
        answer.setText("Your answer: " + a);
        canswer.setText("Correct answer: " + c);
        if(a.equals(c)){
            score.setText("Score: 1/1");
        }else{
            score.setText("Score: 0/1");
        }

        View.OnClickListener buttonListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Answer.this, MainActivity.class);
                startActivity(intent);
            }
        };

        findViewById(R.id.button2).setOnClickListener(buttonListener);

    }



}
