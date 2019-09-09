package life.syang.community.community.controller;

import life.syang.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends BaseController{
    
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/issue")
    public String issue(){
        return "issue";
    }
}
