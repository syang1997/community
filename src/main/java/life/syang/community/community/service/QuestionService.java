package life.syang.community.community.service;

import com.github.pagehelper.PageInfo;
import life.syang.community.community.model.Question;

import java.util.List;

public interface QuestionService {
    void insertQuestion(Question question);

    PageInfo getQuestionList(int num);

    Question queryQuestionById(long id);

    void updateQuestion(Question question1);

    void increaseviewCount(long id);
}
