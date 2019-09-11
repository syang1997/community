package life.syang.community.community.service.impl;

import life.syang.community.community.mapper.CommentMapper;
import life.syang.community.community.model.Comment;
import life.syang.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Map<String, List<Comment>> queryCommentOnQuestion(int id) {
        Map<String, List<Comment>> maps=new HashMap<>();
        List<Comment> list1=commentMapper.queryCommentByQuestionId(id,1);
        if(list1!=null){
            maps.put("grade1",list1);
        }
        List<Comment> list2=commentMapper.queryCommentByQuestionId(id,2);
        if(list2!=null){
            maps.put("grade2",list2);
        }
        List<Comment> list3=commentMapper.queryCommentByQuestionId(id,3);
        if(list3!=null){
            maps.put("grade3",list3);
        }
        return null;
    }

    @Override
    public void insertComment(Comment comment) {
        if(comment!=null){
            commentMapper.insertComment(comment);
        }
    }
}
