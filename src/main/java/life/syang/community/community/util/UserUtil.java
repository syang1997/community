package life.syang.community.community.util;

import life.syang.community.community.model.User;
import life.syang.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class UserUtil {

    @Autowired
    private UserService userService;
    public User onlinUser(HttpServletResponse response, HttpServletRequest request){
        Cookie[] cookies= new Cookie[0];
        if(request.getCookies()==null){
            return null;
        }else {
            cookies=request.getCookies();
        }
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.queryByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        return user;
                    }
                }
            }
        }
        return null;
    }
}
