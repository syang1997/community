package life.syang.community.community.service.impl;

import life.syang.community.community.mapper.NotificationMapper;
import life.syang.community.community.model.Notification;
import life.syang.community.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public void inserNotification(Notification notification) {
        notificationMapper.insertNotification(notification);
    }

    @Override
    public List<Notification> queryNotification(long id) {
        List<Notification> notifications = null;
        List<Notification> notifications2 = null;
        try {
            notifications = notificationMapper.queryNotificationWhenQuetion(id);
            notifications2 = notificationMapper.queryNotificationWhenComment(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(notifications!=null&&notifications2!=null){
            notifications.addAll(notifications2);
        }
        if(notifications==null){
            return notifications;
        }
        Comparator c = new Comparator<Notification>() {
            public int compare(Notification o1, Notification o2) {
                // TODO Auto-generated method stub
                if((long)o1.getGmtCreate()<(int)o2.getGmtCreate()){
                    return 1;
                } else{
                    return -1;
                }
            }
        };
        notifications.sort(c);
        return notifications;
    }
}
