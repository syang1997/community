package life.syang.community.community.controller;

import life.syang.community.community.enums.NotificationStatusEnum;
import life.syang.community.community.enums.NotificationTypeEnum;
import life.syang.community.community.model.*;
import life.syang.community.community.service.CommentService;
import life.syang.community.community.service.NotificationService;
import life.syang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    @ResponseBody
    @PostMapping("/send")
    public BaseInfo sendComment(Comment comment,long questionId){
        User user=userUtil.getUser(request);
        if(user==null){
            return BaseInfo.failInfo("请登陆!",null);
        }
        Question question=questionService.queryQuestionById(questionId);
        Comment comment1 = commentService.queryCommentById(comment.getParentId());
        if(question==null){
            return BaseInfo.failInfo("问题不见了!",null);
        }
        if(comment!=null){
            comment.setCreator(user);
            comment.setGmtModified(System.currentTimeMillis());
            comment.setGmtCreate(comment.getGmtModified());
            try{
                commentService.insertComment(comment);
                if(comment.getType()==1){
                    questionService.increaseCommentCount(questionId);
                    Notification notification=createNotification(question.getId(), user, question.getCreator(),NotificationTypeEnum.REPLY_QUESTION);
                    notificationService.inserNotification(notification);
                }else if(comment.getType()==2){
                    questionService.increaseCommentCount(questionId);
                    commentService.incCommentCount(comment.getParentId());
                    Notification notification=createNotification(comment1.getId(),user,comment1.getCreator(),NotificationTypeEnum.REPLY_COMMENT);
                    notificationService.inserNotification(notification);
                }
                questionService.updataQuestionTime(comment.getGmtModified(),questionId);
            }catch (Exception e){
                return BaseInfo.failInfo("评论出错",null);
            }
        }
        return BaseInfo.successInfo("评论成功!",null);
    }

    @ResponseBody
    @PostMapping("/get")
    public BaseInfo readCommentByQuestion(long id){
         List<Comment> maps= commentService.queryCommentOnQuestionone(id);
        return BaseInfo.successInfo("获取成功!",maps);
    }

    @ResponseBody
    @PostMapping("/get/second")
    public BaseInfo readCommentByComment(long id){
        List<Comment> lists=commentService.queryCommentOnQuestiontwo(id);
        if(lists==null){
            return BaseInfo.failInfo("获取失败",null);
        }
        return BaseInfo.successInfo("获取成功",lists);
    }

    private Notification createNotification(long outerId, User user, User receiver,NotificationTypeEnum notificationTypeEnum) {
        Notification notification=new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationTypeEnum.getType());
        notification.setNotifier(user);
        notification.setOuterId(outerId);
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        return notification;
    }

}
