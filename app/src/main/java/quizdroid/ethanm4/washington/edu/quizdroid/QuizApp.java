package quizdroid.ethanm4.washington.edu.quizdroid;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class QuizApp extends Application {

    public static final String TAG = "QuizApp";
    private TopicStorage repo;


    private static Context context;

    public QuizApp() { }

      @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Application loaded");
        initSingletons();
        QuizApp.context = getApplicationContext();
        repo = new TopicStorage();
    }

    protected void initSingletons() {
        QuizSingleton.initInstance();
    }

    public List<Quiz> getRepo() {
        return repo.getQuizes();
    }

    public static Context getAppContext() {
        return QuizApp.context;
    }

    public TopicStorage getRepository() {

        return repo;

    }

}
