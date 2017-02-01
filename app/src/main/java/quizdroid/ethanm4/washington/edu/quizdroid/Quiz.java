package quizdroid.ethanm4.washington.edu.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 1/30/2017.
 */

public class Quiz implements Serializable {

    private String description;
    private int questionCount;
    private ArrayList<Question> questions = new ArrayList<Question>();

    public Quiz(){
        description = "";
        questionCount = 0;
    }
    public Question getQuestion() {
        return questions.get(0);
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

}
