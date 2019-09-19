package life.syang.community.community.controller;

import life.syang.community.community.model.User;
import life.syang.community.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notification")
@Controller
public class NotificationController extends BaseController{

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/read/{id}")
    public String readNotifiactionToQuestion(@PathVariable("id") long id){
        User user= userUtil.getUser(request);
        if(user==null){
            return "redirect:/";
        }
        int flag=notificationService.readOneNotification(id,user);
            long goId=notificationService.queryNotifiactionByid(id).getOuterId();
            return "redirect:/question/"+goId;
    }


}
