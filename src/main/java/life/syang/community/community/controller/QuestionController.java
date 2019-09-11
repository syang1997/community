package life.syang.community.community.controller;

import com.github.pagehelper.PageInfo;
import life.syang.community.community.exception.CustomizeErrorCode;
import life.syang.community.community.exception.CustomizeException;
import life.syang.community.community.model.BaseInfo;
import life.syang.community.community.model.Question;
import life.syang.community.community.model.User;
import life.syang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController{
    @Autowired
    private QuestionService questionService;


    @ResponseBody
    @PostMapping("/publish")
    public BaseInfo issueQuestion(Question question){
        if(question!=null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            User user=userUtil.getUser(request);
            if(user==null){
                return BaseInfo.failInfo("未登陆!",null);
            }
            try {
                question.setCreator(user);
                questionService.insertQuestion(question);
            } catch (Exception e) {
                e.printStackTrace();
                return BaseInfo.failInfo("发布出错!",null);
            }
        }
        return BaseInfo.successInfo("发布成功!",null);
    }


    @GetMapping("/redact/{id}")
    public String updataQuestion(@PathVariable(name = "id") long id,Model model){
            User user=userUtil.getUser(request);
            if (user!=null){
               Question question=questionService.queryQuestionById(id);
               Boolean b=question.getCreator().getAccountId().equals(user.getAccountId());
               Boolean c=question.getCreator().getAccountId()==user.getAccountId();
               if (question.getCreator().getAccountId().equals(user.getAccountId())){
                   model.addAttribute(question);
                   return "issue";
               }
            }else {
                throw new CustomizeException(CustomizeErrorCode.USER_NOT_LOGIN);
            }
        return null;
    }

    @ResponseBody
    @PostMapping("/list/{num}")
    public BaseInfo QuestionList(@PathVariable(name = "num") int num){
        PageInfo pageInfo;
        try {
            pageInfo=questionService.getQuestionList(num);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseInfo.failInfo("查询异常",null);
        }
        return BaseInfo.successInfo("成功",pageInfo);
    }

    @GetMapping("/{id}")
    public String question(@PathVariable(name="id") long id, Model model){
            Question question=questionService.queryQuestionById(id);
            questionService.increaseviewCount(id);
                model.addAttribute(question);
                return "question";
    }

    @ResponseBody
    @PostMapping("/update")
    public BaseInfo UpdateQuestion(Question question){
            User user=userUtil.getUser(request);
            if(user!=null){
                Question question1=questionService.queryQuestionById(question.getId());
                if (question1.getCreator().getId()==user.getId()){
                    questionService.updateQuestion(question);
                }else {
                    return BaseInfo.failInfo("请登陆",null);
            }
            return BaseInfo.failInfo("修改出错",null);
        }
        return BaseInfo.successInfo("修改成功",null);
    }
}
