package life.syang.community.community.util;

import life.syang.community.community.model.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtil {
    public User getUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("user");
    }
}
