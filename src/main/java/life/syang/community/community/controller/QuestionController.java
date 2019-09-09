package life.syang.community.community.controller;

import com.github.pagehelper.PageInfo;
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
            User user= (User) request.getSession().getAttribute("user");
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
    public String question(@PathVariable(name="id") Integer id, Model model){
        try {
            User user= (User) request.getSession().getAttribute("user");
            if(user!=null){
                model.addAttribute(questionService.queryQuestionById(id));
                return "question";
            }
        }catch (Exception e){
            return "index";//跳转错误页面
        }
        return null;
    }
}
