package life.syang.community.community.controller;

import life.syang.community.community.mapper.UserMapper;
import life.syang.community.community.model.User;
import life.syang.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String index(HttpServletResponse response, HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.queryByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "index";
    }

    @GetMapping("/issue")
    public String issue(){
        return "issue";
    }
}
