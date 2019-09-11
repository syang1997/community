package life.syang.community.community.controller;

import com.github.pagehelper.PageInfo;
import life.syang.community.community.model.BaseInfo;
import life.syang.community.community.model.User;
import life.syang.community.community.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController extends BaseController{
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{action}")
    public String profile(@PathVariable(name = "action")String action, Model model){
        String msg="";
        switch (action){
            case "questions":
                msg="我的提问";
                break;
            case "replies":
                msg="最新回复";
                break;
            default:
                msg="我的提问";
        }
            model.addAttribute("section",action);
            model.addAttribute("sectionName",msg);
        return "profile";
    }

    @PostMapping("/myQuestions")
    @ResponseBody
    public BaseInfo getMyQuestions(int pn){
        PageInfo pageInfo;
        try {
            User user=userUtil.getUser(request);
            if(user==null){
                return BaseInfo.failInfo("未登陆",null);
            }
             pageInfo=profileService.queryQuestionById(user.getId(),pn);
        }catch (Exception e){
            return BaseInfo.failInfo("查询我的问题失败!",null);
        }
        return BaseInfo.successInfo("成功",pageInfo);
    }
}
