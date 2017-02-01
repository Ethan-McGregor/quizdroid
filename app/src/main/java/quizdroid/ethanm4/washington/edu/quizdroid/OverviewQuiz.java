package quizdroid.ethanm4.washington.edu.quizdroid;

import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OverviewQuiz extends AppCompatActivity {
    boolean overviewQuizFragOn = false;
    boolean takeQuizFragOn = false;
    boolean answerFragOn = false;
    private Fragment fragToDisplay = null;
    private int count;
   private String dis;
    private String answer;
    private String correctAnswer;
    private boolean back;
    TakeQuizFrag n = new TakeQuizFrag();
    Quiz temp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_quiz);
        temp  = (Quiz) getIntent().getSerializableExtra("quiz");
        dis = temp.getDescription();
        count = temp.getQuestionCount();
        Button btnFragment = (Button)findViewById(R.id.btnBegin);
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragToDisplay = null;
                if(overviewQuizFragOn){
                    fragToDisplay = n;
                    overviewQuizFragOn = false;
                    takeQuizFragOn = true;

                }else if(takeQuizFragOn){
                    answer= n.getAnswer();
                    correctAnswer = n.getCorrectAnswer();
                    fragToDisplay = new AnswerFrag();
                    Bundle args = new Bundle();
                    args.putString("answer", answer);
                    args.putString("cAnswer", correctAnswer);
                    fragToDisplay.setArguments(args);
                    answerFragOn = true;
                    takeQuizFragOn = false;

                }else{
                    back = true;
                }


                if(back){
                    Intent intent = new Intent(OverviewQuiz.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    tx.replace(R.id.fragment_placeholder, fragToDisplay);
                    tx.commit();
                }
                
            }

        });

        Bundle args = new Bundle();
        args.putString("dis", dis);
        args.putInt("count", count);
        overviewQuizFragOn = true;
        OverviewQuizFrag ff = new OverviewQuizFrag();
        ff.setArguments(args);
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, ff);
        tx.commit();
    }

}
