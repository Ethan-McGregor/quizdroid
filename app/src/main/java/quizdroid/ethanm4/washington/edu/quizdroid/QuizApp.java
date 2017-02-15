package quizdroid.ethanm4.washington.edu.quizdroid;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.List;

public class QuizApp extends Application {

    public static final String TAG = "QuizApp";
    private TopicStorage repo;
    String json;
    private static Context context;

    public QuizApp() { }

    public void initializeRepo(final Activity activity) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String url;
        url = prefs.getString("json_url", "https://tednewardsandbox.site44.com/questions.json");
        if (url == "" ) { // if blank in preferences, reset to default
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("json_url", "https://tednewardsandbox.site44.com/questions.json");
            editor.commit();
            url = "https://tednewardsandbox.site44.com/questions.json";
        }

        GetJSON sh = new GetJSON();

        // making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url);

        Log.e("EH", "Response from url: " + jsonStr);
        json = jsonStr;

    }    @Override
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

}
