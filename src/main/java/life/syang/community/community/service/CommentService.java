package life.syang.community.community.service;

import life.syang.community.community.model.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    Map<String, List<Comment>> queryCommentOnQuestion(long id);

    List<Comment> queryCommentOnQuestionone(long id);

    List<Comment> queryCommentOnQuestiontwo(long id);

    void insertComment(Comment comment);

    void incCommentCount(long id);

    Comment queryCommentById(long id);
}
