package life.syang.community.community.service;

import com.github.pagehelper.PageInfo;
import life.syang.community.community.model.Notification;
import life.syang.community.community.model.User;
import org.apache.ibatis.annotations.Param;

public interface NotificationService {

    void inserNotification(Notification notification);

    PageInfo queryNotification(long id, int num);

    int readOneNotification(long id, User user);

    Notification queryNotifiactionByid(long id);

    int queryUnreadCount(long id);
}
