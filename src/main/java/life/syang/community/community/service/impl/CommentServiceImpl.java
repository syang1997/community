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
    public Map<String, List<Comment>> queryCommentOnQuestion(long id) {
        Map<String, List<Comment>> maps=new HashMap<>();
        List<Comment> list1=commentMapper.queryCommentByQuestionId(id);
        if(list1!=null){
            maps.put("grade1",list1);
            List<Comment> list2=commentMapper.queryCommentByQuestionIdtwo(id);
            if(list2!=null){
                maps.put("grade2",list2);
                List<Comment> list3=commentMapper.queryCommentByQuestionIdthree(id);
                if(list3!=null){
                    maps.put("grade3",list3);
                }
            }

        }

        return maps;
    }

    @Override
    public void insertComment(Comment comment) {
        if(comment!=null){
            commentMapper.insertComment(comment);
        }
    }
}
