package life.syang.community.community.controller;

import life.syang.community.community.mapper.UserMapper;
import life.syang.community.community.model.User;
import life.syang.community.community.service.UserService;
import life.syang.community.community.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController extends BaseController{
    
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String index(){
        userUtil.onlinUser(response,request);
        return "index";
    }

    @GetMapping("/issue")
    public String issue(){
        return "issue";
    }
}
