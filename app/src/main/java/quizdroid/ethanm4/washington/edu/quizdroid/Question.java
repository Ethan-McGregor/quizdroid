package quizdroid.ethanm4.washington.edu.quizdroid;

import java.io.Serializable;

/**
 * Created by Ethan on 1/30/2017.
 */

public class Question implements Serializable {


    private String question;
    private String answer;
    private String fakeAnswer1;
    private String fakeAnswer2;
    private String fakeAnswer3;

    public Question(String question,String answer, String fakeAnswer1,String fakeAnswer2,String fakeAnswer3){
        this.answer = answer;
        this.question = question;
        this.fakeAnswer1 = fakeAnswer1;
        this.fakeAnswer2 = fakeAnswer2;
        this.fakeAnswer3 = fakeAnswer3;
    }
    public Question(){
        this.answer = "";
        this.question = "";
        this.fakeAnswer1 = "";
        this.fakeAnswer2 = "";
        this.fakeAnswer3 = "";
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public String getFakeAnswer2() {
        return fakeAnswer2;
    }

    public String getFakeAnswer3() {
        return fakeAnswer3;
    }

    public String getFakeAnswer1() {
        return fakeAnswer1;
    }

    public String getAnswer() {
        return answer;
    }

    public void setFakeAnswer2(String fakeAnswer2) {
        this.fakeAnswer2 = fakeAnswer2;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setFakeAnswer1(String fakeAnswer1) {
        this.fakeAnswer1 = fakeAnswer1;
    }

    public void setFakeAnswer3(String fakeAnswer3) {
        this.fakeAnswer3 = fakeAnswer3;
    }

}
