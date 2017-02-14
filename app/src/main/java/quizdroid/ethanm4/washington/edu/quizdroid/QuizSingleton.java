package quizdroid.ethanm4.washington.edu.quizdroid;

/**
 * Created by Ethan on 2/13/2017.
 */

public class QuizSingleton {

    private static QuizSingleton instance;

    public static void initInstance() {
        if (instance == null) {
            instance = new QuizSingleton();
        }
    }

    public static QuizSingleton getInstance() {
        // Return the instance
        return instance;
    }

    private QuizSingleton() {
    }

}