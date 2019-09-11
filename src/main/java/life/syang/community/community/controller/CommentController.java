package life.syang.community.community.controller;

import life.syang.community.community.model.BaseInfo;
import life.syang.community.community.model.Comment;
import life.syang.community.community.model.Question;
import life.syang.community.community.model.User;
import life.syang.community.community.service.CommentService;
import life.syang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @PostMapping("/send")
    public BaseInfo sendComment(Comment comment){
        User user=userUtil.getUser(request);
        if(user==null){
            return BaseInfo.failInfo("请登陆后评论!",null);
        }
        if(comment!=null){
            comment.setCommentator(user);
            commentService.insertComment(comment);
        }
        return BaseInfo.successInfo("评论成功!",null);
    }

    @ResponseBody
    @PostMapping
    public BaseInfo readCommentByQuestion(long id){
        questionService.queryQuestionById(id);

        return BaseInfo.successInfo("获取成功!",null);
    }

}
