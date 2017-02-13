package quizdroid.ethanm4.washington.edu.quizdroid;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 2/12/2017.
 */
public class QuizApp extends Application {
    private static QuizApp ourInstance = new QuizApp();

    private static Context context;

    public static QuizApp getInstance() {
        return ourInstance;
    }
    private QuizApp() {
    }
        protected void onCreate(Bundle savedInstanceState) {
        Log.v("QuizAPP","Fired");
    }

    public List<Quiz> getRepo() {
    ArrayList<Quiz> list = new ArrayList<Quiz>();
        Quiz one = new Quiz();
        Quiz two = new Quiz();
        Quiz three = new Quiz();
        list.add(one);
        list.add(two);
        list.add(three);
    return list;
    }

    public static Context getAppContext() {

        return QuizApp.context;

    }

}
