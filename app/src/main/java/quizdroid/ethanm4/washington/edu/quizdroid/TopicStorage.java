package quizdroid.ethanm4.washington.edu.quizdroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ethan on 2/13/2017.
 */

public class TopicStorage implements TopicRepository{

    ArrayList<Quiz> list = new ArrayList<Quiz>();
    private String url;
    private List<Quiz> topics = new ArrayList<>();

public TopicStorage(){
    createTopics();
}



    public void createTopics() {
        int b= android.R.drawable.ic_dialog_email;
        int c= android.R.drawable.ic_btn_speak_now;
        int d= android.R.drawable.ic_input_add;

        Question q = new Question("This is a test","one","two","three","four", 4);
        Quiz one = new Quiz("Math","This is a math test!",b);
        one.addQuestion(q);
        one.addQuestion(q);
        one.addQuestion(q);
        Quiz two = new Quiz("Superheros","how well do you know them?",c);
        two.addQuestion(q);
        Quiz three = new Quiz("physics","like math but harder",d);
        three.addQuestion(q);
        list.add(one);
        list.add(two);
        list.add(three);
        GetJSON data = new GetJSON();
        String questions = data.makeServiceCall("https://tednewardsandbox.site44.com/questions.json");
        System.out.println(questions);
        Log.v("here","HEEEEE");
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


}
