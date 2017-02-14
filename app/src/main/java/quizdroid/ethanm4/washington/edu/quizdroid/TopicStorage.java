package quizdroid.ethanm4.washington.edu.quizdroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ethan on 2/13/2017.
 */

public class TopicStorage implements TopicRepository{

    ArrayList<Quiz> list = new ArrayList<Quiz>();

public TopicStorage(){
    createTopics();
}


    public void createTopics() {
        Question q = new Question("This is a test","one","two","three","four", 4);
        Quiz one = new Quiz("Math","This is a math test!");
        one.addQuestion(q);
        one.addQuestion(q);
        one.addQuestion(q);
        Quiz two = new Quiz("Superheros","how well do you know them?");
        two.addQuestion(q);
        Quiz three = new Quiz("physics","like math but harder");
        three.addQuestion(q);
        list.add(one);
        list.add(two);
        list.add(three);
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
