package quizdroid.ethanm4.washington.edu.quizdroid;

import java.io.Serializable;

/**
 * Created by Ethan on 1/30/2017.
 */

public class Question implements Serializable {


    private String question;

    private String fakeAnswer1;
    private String fakeAnswer2;
    private String fakeAnswer3;
    private String fakeAnswer4;
    private int numOfAnswer;

    public Question(String question, String fakeAnswer1,String fakeAnswer2,String fakeAnswer3, String fakeAnswer4, int numOfAnswer){

        this.question = question;
        this.fakeAnswer1 = fakeAnswer1;
        this.fakeAnswer2 = fakeAnswer2;
        this.fakeAnswer3 = fakeAnswer3;
        this.fakeAnswer4 = fakeAnswer4;
        this.numOfAnswer = numOfAnswer;
    }
    public Question(){

        this.question = "";
        this.fakeAnswer1 = "";
        this.fakeAnswer2 = "";
        this.fakeAnswer3 = "";
        this.fakeAnswer4 = "";
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
    public String getFakeAnswer4() {
        return fakeAnswer4;
    }


    public String getAnswer() {
        if(numOfAnswer == 1){
            return fakeAnswer1;
        }else if(numOfAnswer == 2){
            return fakeAnswer2;
        }else if(numOfAnswer == 3){
            return fakeAnswer3;
        }else if (numOfAnswer == 4){
            return fakeAnswer4;
        }

            return null;
    }

    public void setFakeAnswer2(String fakeAnswer2) {
        this.fakeAnswer2 = fakeAnswer2;
    }


    public void setFakeAnswer1(String fakeAnswer1) {
        this.fakeAnswer1 = fakeAnswer1;
    }

    public void setFakeAnswer3(String fakeAnswer3) {
        this.fakeAnswer3 = fakeAnswer3;
    }
    public void setFakeAnswer4(String fakeAnswer3) {
        this.fakeAnswer3 = fakeAnswer4;
    }

}
