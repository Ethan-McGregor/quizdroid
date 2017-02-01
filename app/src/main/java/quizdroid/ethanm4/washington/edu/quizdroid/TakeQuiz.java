package quizdroid.ethanm4.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import java.io.Serializable;

public class TakeQuiz extends AppCompatActivity implements Serializable{
    private String answer;
    private String correctAnswer = "2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        Quiz temp = (Quiz) getIntent().getSerializableExtra("quiz");
        RadioButton rOne = (RadioButton) findViewById(R.id.aOne);
        RadioButton rTwo = (RadioButton) findViewById(R.id.aTwo);
        RadioButton rThree = (RadioButton) findViewById(R.id.aThree);
        RadioButton rFour = (RadioButton) findViewById(R.id.aFour);
        TextView qOne = (TextView) findViewById(R.id.qOne);




                qOne.setText("what is 1 + 1?");
                rOne.setText("3");
                rTwo.setText("2");
                rThree.setText("1.5");
                rFour.setText("2.1");

                OnClickListener buttonListener = new OnClickListener() {
                    RadioButton rOne = (RadioButton) findViewById(R.id.aOne);
                    RadioButton rTwo = (RadioButton) findViewById(R.id.aTwo);
                    RadioButton rThree = (RadioButton) findViewById(R.id.aThree);
                    RadioButton rFour = (RadioButton) findViewById(R.id.aFour);

                    public void onClick(View v) {

                        Button submit = (Button) findViewById(R.id.button);
                        switch (v.getId()) {
                            case R.id.aOne:
                                submit.setVisibility(View.VISIBLE);
                                if (rTwo.isChecked() || rThree.isChecked() || rFour.isChecked()) {
                                    rTwo.setChecked(false);
                                    rThree.setChecked(false);
                                    rFour.setChecked(false);
                                }
                                rOne.setChecked(true);
                                answer = rOne.getText() + "";
                                break;
                            case R.id.aTwo:
                                submit.setVisibility(View.VISIBLE);
                                if (rOne.isChecked() || rThree.isChecked() || rFour.isChecked()) {
                                    rOne.setChecked(false);
                                    rThree.setChecked(false);
                                    rFour.setChecked(false);
                                }
                                rTwo.setChecked(true);
                                answer = rTwo.getText() + "";
                                break;
                            case R.id.aThree:
                                submit.setVisibility(View.VISIBLE);
                                if (rTwo.isChecked() || rOne.isChecked() || rFour.isChecked()) {
                                    rTwo.setChecked(false);
                                    rOne.setChecked(false);
                                    rFour.setChecked(false);
                                }
                                rThree.setChecked(true);
                                answer = rThree.getText() + "";
                                break;
                            case R.id.aFour:
                                submit.setVisibility(View.VISIBLE);
                                if (rTwo.isChecked() || rThree.isChecked() || rOne.isChecked()) {
                                    rTwo.setChecked(false);
                                    rThree.setChecked(false);
                                    rOne.setChecked(false);
                                }
                                rFour.setChecked(true);
                                answer = rFour.getText() + "";
                                break;
                        }
                        if (v.getId() == R.id.button) {


                                Intent intent = new Intent(TakeQuiz.this, Answer.class);
                                intent.putExtra("answer", answer);
                                intent.putExtra("cAnswer", correctAnswer);
                                startActivity(intent);

                        }
                    }
                };

                findViewById(R.id.aOne).setOnClickListener(buttonListener);
                findViewById(R.id.aTwo).setOnClickListener(buttonListener);
                findViewById(R.id.aThree).setOnClickListener(buttonListener);
                findViewById(R.id.aFour).setOnClickListener(buttonListener);
                findViewById(R.id.button).setOnClickListener(buttonListener);
            }



}
