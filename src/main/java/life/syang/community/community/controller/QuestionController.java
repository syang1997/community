package life.syang.community.community.controller;

import life.syang.community.community.mapper.QuestionMapper;
import life.syang.community.community.model.BaseInfo;
import life.syang.community.community.model.Question;
import life.syang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @PostMapping("/issue")
    public BaseInfo issueQuestion(Question question){
        if(question!=null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            try {
                questionService.insertQuestion(question);
            } catch (Exception e) {
                return BaseInfo.failInfo("发布出错!",null);
            }
        }
        return BaseInfo.successInfo("发布成功!",null);
    }
}
