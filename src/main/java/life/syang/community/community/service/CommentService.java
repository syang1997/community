package life.syang.community.community.service;

import life.syang.community.community.model.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    Map<String, List<Comment>> queryCommentOnQuestion(int id);

    void insertComment(Comment comment);
}
