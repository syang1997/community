package life.syang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

    @GetMapping("/profile/{action}")
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
}
