package quizdroid.ethanm4.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            Intent intent = new Intent(MainActivity.this, OverviewQuiz.class);
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.math:
                        Quiz math = createMath();
                        intent.putExtra("quiz", math);
                        break;
                    case R.id.physics:
                        Quiz physics = createPhysics();
                        intent.putExtra("quiz", physics);
                        break;
                    case R.id.msh:
                        Quiz msh = createMSH();
                        intent.putExtra("quiz", msh);
                        break;
                    case R.id.java:
                        Quiz java = createJava();
                        intent.putExtra("quiz", java);
                        break;
                    case R.id.android:
                        Quiz android = createAndroid();
                        intent.putExtra("quiz", android);
                        break;
                }


                startActivity(intent);

            }
        };

        findViewById(R.id.math).setOnClickListener(buttonListener);

        findViewById(R.id.physics).setOnClickListener(buttonListener);

        findViewById(R.id.msh).setOnClickListener(buttonListener);
        findViewById(R.id.java).setOnClickListener(buttonListener);
        findViewById(R.id.android).setOnClickListener(buttonListener);


    }



    public Quiz createMath(){
        String description = "This is a little math quiz, it will be simple.";
        int questionCount = 1;
        Quiz math = new Quiz(description,questionCount);

        return math;
    }
    public Quiz createPhysics(){
        String description = "This is a little physics quiz, it will be simple.";
        int questionCount = 1;
        Quiz physics = new Quiz(description,questionCount);
        return physics;
    }
    public Quiz createMSH(){
        String description = "This is a little Marvle Super Hero quiz, it will be simple.";
        int questionCount = 1;
        Quiz msh = new Quiz(description,questionCount);
        return msh;
    }
    public Quiz createJava(){
        String description = "This is a little Java quiz, it will be simple.";
        int questionCount = 1;
        Quiz java = new Quiz(description,questionCount);
        return java;
    }
    public Quiz createAndroid(){
        String description = "This is a little android quiz, it will be simple.";
        int questionCount = 1;
        Quiz android = new Quiz(description,questionCount);
        return android;
    }
}
