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
    private Fragment fragToDisplay = null;
    private int count;
   private String dis;
    private boolean back;
    private int questionCount;
    private int numCorrect;
    private  Button btnFragment;
    TakeQuizFrag tq = new TakeQuizFrag();
    OverviewQuizFrag oq = new OverviewQuizFrag();
    Quiz temp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_quiz);
        //Intent vars
        temp  = (Quiz) getIntent().getSerializableExtra("topic");
        dis = temp.getDescription();
        count = temp.getQuestions().size();
        questionCount = 0;

        //Button
         btnFragment = (Button)findViewById(R.id.btnBegin);
        btnFragment.setText("Continue");

        //initial screen
        Bundle args = new Bundle();
        args.putString("dis", dis);

        args.putInt("count", count);
        overviewQuizFragOn = true;
        OverviewQuizFrag start = new OverviewQuizFrag();
        renderFrag(args,start);
        overviewQuizFragOn = true;


        btnFragment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(back){
                    Intent intent = new Intent(OverviewQuiz.this, MainActivity.class);
                    startActivity(intent);
                }

                if(questionCount >= 1 && questionCount <= 3){
                    String answer = tq.getAnswer();
                    if(answer == null){

                      questionCount--;
                    }else {
                        if (answer.equals(temp.getQuestions().get(questionCount-1).getAnswer())) {
                            numCorrect++;

                        }

                    }
                }
                if(questionCount < count){
                    btnFragment.setText("Next Question");
                    Bundle args = new Bundle();
                    args.putString("question", temp.getQuestions().get(questionCount).getQuestion());
                    args.putString("one", temp.getQuestions().get(questionCount).getFakeAnswer1());
                    args.putString("two", temp.getQuestions().get(questionCount).getFakeAnswer2());
                    args.putString("three", temp.getQuestions().get(questionCount).getFakeAnswer3());
                    args.putString("four", temp.getQuestions().get(questionCount).getFakeAnswer4());
                    tq = new TakeQuizFrag();
                    renderFrag(args,tq);
                    questionCount++;
                    Log.v("if",numCorrect + "");

                }else{
                    Log.v("HERE",numCorrect + "");


                    fragToDisplay = new AnswerFrag();
                    Bundle args = new Bundle();
                    args.putString("numCorrect", numCorrect +"");
                    args.putString("total", temp.getQuestions().size() +"");
                    renderFrag(args,fragToDisplay);
                    back = true;
                }



            }

        });


    }

    public  void renderFrag(Bundle args, android.app.Fragment frag){
        frag.setArguments(args);
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, frag);
        tx.commit();
    }

}
