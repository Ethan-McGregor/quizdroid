package quizdroid.ethanm4.washington.edu.quizdroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ethan on 2/13/2017.
 */

public class TopicStorage implements TopicRepository{

    ArrayList<Quiz> list;
    private String url;
    private List<Quiz> topics = new ArrayList<>();
    private  String json;

public TopicStorage(){
    createTopics();
}



    public void createTopics() {
//        int b= android.R.drawable.ic_dialog_email;
//        int c= android.R.drawable.ic_btn_speak_now;
//        int d= android.R.drawable.ic_input_add;
//
//        Question q = new Question("This is a test","one","two","three","four", 4);
//        Quiz one = new Quiz("Math","This is a math test!",b);
//        one.addQuestion(q);
//        one.addQuestion(q);
//        one.addQuestion(q);
//        Quiz two = new Quiz("Superheros","how well do you know them?",c);
//        two.addQuestion(q);
//        Quiz three = new Quiz("physics","like math but harder",d);
//        three.addQuestion(q);
//        list.add(one);
//        list.add(two);
//        list.add(three);
//        GetJSON data = new GetJSON();
//        String questions = data.makeServiceCall("https://tednewardsandbox.site44.com/questions.json");
//        System.out.println(questions);
//        Log.v("here","HEEEEE");
        list =  new ArrayList<Quiz>();
    }



    public ArrayList<Quiz> getQuizes() {

        return list;
    }

    public Quiz getTopic(int i)  {
        return list.get(i);

    }
    public void updateTopic(Quiz t, Question q) {
//        int orig = topics.indexOf(original);
//        topics.set(orig, updated);
    }

    public void deleteTopic(Quiz T) {
//        topics.remove(T);
    }


    public void initializeRepo(final Activity activity) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String url;
        url = prefs.getString("json_url", "https://tednewardsandbox.site44.com/questions.json");
        if (url == "") { // if blank in preferences, reset to default
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


        if (jsonStr != null) {

            try {

                JSONArray topics = new JSONArray(jsonStr);
                for (int i = 0; i < topics.length(); i++) {
                    JSONObject topic = topics.getJSONObject(i);
                    String title = topic.getString("title");
                    String desc = topic.getString("desc");
                    Quiz newTopic = new Quiz(title, desc, R.drawable.ic_audiotrack);

                    JSONArray questions = topic.getJSONArray("questions");
                    for (int j = 0; j < questions.length(); j++) {
                        JSONObject question = questions.getJSONObject(j);
                        String text = question.getString("text");
                        int answer = Integer.parseInt(question.getString("answer"));


                        JSONArray answers = question.getJSONArray("answers");
                        int lengthAnswers = answers.length();
                        String[] answersArray = new String[lengthAnswers];
                        Log.v("THIS",answersArray[0] + "");
                        for (int k = 0; k < answers.length(); k++) {

                            answersArray[k] = answers.getString(k);

                        }
                        newTopic.addQuestion(new Question(text, answersArray[0] , answersArray[1], answersArray[2], answersArray[3], answer));

                    }
                    ((QuizApp) activity.getApplication()).getRepository().getQuizes().add(newTopic);
                }
                Log.d(QuizApp.TAG, topics.toString());
            } catch (final JSONException e) {
                Log.e("ERROR", "Json parsing error: " + e.getMessage());
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(activity,
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }

                });

            }
        } else {

            Log.e("ERROR", "Couldn't get json from server.");
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity,
                            "Couldn't get json from server. Check your JSON URL under Preferences.",
                            Toast.LENGTH_LONG)
                            .show();
                }

            });


        }


    }
    public List<Quiz> getAllTopics() {

        return list;

    }
    }
