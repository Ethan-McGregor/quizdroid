package quizdroid.ethanm4.washington.edu.quizdroid;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 1/30/2017.
 */

public class Quiz implements Serializable {

    private String title;
    private String shortDis;
    private String longDis;
    private String description;
    private int questionCount;
    private int icon;
//    private ImageView icon;
    private ArrayList<Question> questions = new ArrayList<Question>();

    public Quiz(){
        description = "";
        questionCount = 0;
    }
    public Quiz(String title, String discription, int icon){
        description = discription;
        this.title = title;
        this.icon = icon;
    }
    public Question getQuestion() {
        return questions.get(0);
    }
    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Quiz(String description, int questionCount){
        this.description = description;
        this.questionCount = questionCount;
    }


    public int getQuestionCount(){
        return questionCount;
    }

    public String getDescription(){
        return description;
    }
    public String getTitle(){
        return title;
    }
    public int getIcon(){
        return icon;
    }

}
