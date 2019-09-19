package life.syang.community.community.controller;

import com.github.pagehelper.PageInfo;
import life.syang.community.community.cache.TagCache;
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

import java.util.List;

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
                return BaseInfo.failInfo("请登陆!",null);
            }
            try {
                question.setCreator(user);
                if("".equals(TagCache.isValid(question.getTag()))){
                    return BaseInfo.failInfo("标签错误",null);
                }else {
                    questionService.insertQuestion(question);
                    return BaseInfo.successInfo("发布成功!",null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return BaseInfo.failInfo("发布出错!",null);
            }
        }
        return BaseInfo.failInfo("发布出错!",null);
    }


    @GetMapping("/redact/{id}")
    public String updataQuestion(@PathVariable(name = "id") long id,Model model){
            User user=userUtil.getUser(request);
            if (user!=null){
               Question question=questionService.queryQuestionById(id);
               Boolean b=question.getCreator().getAccountId().equals(user.getAccountId());
               Boolean c=question.getCreator().getAccountId()==user.getAccountId();
               if (question.getCreator().getAccountId().equals(user.getAccountId())){
                   model.addAttribute("question",question);
                   model.addAttribute("tags", TagCache.get());
                   return "/issue";
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

    @ResponseBody
    @PostMapping("/search")
    public BaseInfo QuestionSearch(String search,int pn){
        PageInfo pageInfo;
        try {
            if(!"".equals(search)){
                pageInfo=questionService.SearchQuestion(search,pn);
            }else {
                return BaseInfo.failInfo("查询异常",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseInfo.failInfo("查询异常",null);
        }
        if(pageInfo.getList().size()==0){
            return BaseInfo.successInfo("0000",null);
        }
        return BaseInfo.successInfo("成功",pageInfo);
    }

    @GetMapping("/{id}")
    public String question(@PathVariable(name="id") long id, Model model){
            Question question=questionService.queryQuestionById(id);
            questionService.increaseviewCount(id);
                model.addAttribute("question",question);
            List<Question> lists=null;
            if(question!=null||"".equals(question.getTag())){
                lists=questionService.queryLikeTagQuestion(question);
            }
            if(lists!=null){
                model.addAttribute("recommend",lists);
            }
                return "question";
    }

//    @ResponseBody
//    @PostMapping("/recommend")
//    public BaseInfo recommendQuestion(Question question){
//        List<Question> lists=null;
//        if(question!=null||"".equals(question.getTag())){
//            lists=questionService.queryLikeTagQuestion(question);
//        }
//        if(lists!=null){
//            return BaseInfo.successInfo("推荐成功",lists);
//        }
//        return BaseInfo.failInfo("没有推荐",null);
//    }

    @ResponseBody
    @PostMapping("/update")
    public BaseInfo UpdateQuestion(Question question){
            User user=userUtil.getUser(request);
            if(user!=null){
                Question question1=questionService.queryQuestionById(question.getId());
                if (question1.getCreator().getId()==user.getId()){
                    if("".equals(TagCache.isValid(question.getTag()))){
                        return BaseInfo.failInfo("标签错误",null);
                    }else {
                        questionService.updateQuestion(question);
                        return BaseInfo.successInfo("修改成功",null);
                    }
                }
            return BaseInfo.failInfo("修改出错",null);
        }
        return BaseInfo.failInfo("请登陆!",null);
    }
}
