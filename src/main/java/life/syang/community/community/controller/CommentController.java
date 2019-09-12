package life.syang.community.community.controller;

import life.syang.community.community.model.BaseInfo;
import life.syang.community.community.model.Comment;
import life.syang.community.community.model.User;
import life.syang.community.community.service.CommentService;
import life.syang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @Transactional
    @ResponseBody
    @PostMapping("/send")
    public BaseInfo sendComment(Comment comment,long questionsId){
        User user=userUtil.getUser(request);
        if(user==null){
            return BaseInfo.failInfo("请登陆后评论!",null);
        }
        if(comment!=null){
            comment.setCreator(user);
            commentService.insertComment(comment);
            questionService.increaseCommentCount(questionsId);
        }
        return BaseInfo.successInfo("评论成功!",null);
    }

    @ResponseBody
    @PostMapping("/get")
    public BaseInfo readCommentByQuestion(long id){
        Map<String, List<Comment>> maps= commentService.queryCommentOnQuestion(id);
        return BaseInfo.successInfo("获取成功!",maps);
    }

}
