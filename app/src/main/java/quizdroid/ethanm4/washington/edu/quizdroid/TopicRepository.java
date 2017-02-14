package quizdroid.ethanm4.washington.edu.quizdroid;

import java.util.List;

/**
 * Created by Ethan on 2/12/2017.
 */

public interface TopicRepository {
    public void createTopics();
    public List<Quiz> getQuizes();
    public void updateTopic(Quiz t, Question q);
    public void deleteTopic(Quiz t);
}
