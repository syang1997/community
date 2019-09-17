package life.syang.community.community.controller;

import life.syang.community.community.model.BaseInfo;
import life.syang.community.community.model.Notification;
import life.syang.community.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @RequestMapping("/{id}")
    public BaseInfo getNotificatons(@PathVariable(name = "id") long id){
        List<Notification> notifications = notificationService.queryNotification(id);
        if (notifications!=null){
            return BaseInfo.successInfo("成功",notifications);
        }
        return BaseInfo.failInfo("失败",notifications);
    }

}
