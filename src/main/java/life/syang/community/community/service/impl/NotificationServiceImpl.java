package life.syang.community.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.syang.community.community.mapper.NotificationMapper;
import life.syang.community.community.model.Notification;
import life.syang.community.community.model.User;
import life.syang.community.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;
    @Value("${myconfig.page-size}")
    private int pageSize;

    @Override
    public void inserNotification(Notification notification) {
        notificationMapper.insertNotification(notification);
    }

    @Override
    public PageInfo queryNotification(long id,int num) {
        PageHelper.startPage(num,pageSize);
        List<Notification> notifications =notificationMapper.queryNotificationWhenQuetion(id);
        PageInfo page = new PageInfo(notifications);
        return page;
    }

    @Override
    public int readOneNotification(long id, User user) {
        if(user!=null){
            return notificationMapper.readOne(id,user.getId());
        }
        return 0;
    }

    @Override
    public Notification queryNotifiactionByid(long id) {
        return notificationMapper.queryNotifiactionByid(id);
    }

    @Override
    public int queryUnreadCount(long id) {
        return notificationMapper.queryUnreadCount(id);
    }


}
