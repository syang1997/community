package life.syang.community.community.service;

import life.syang.community.community.model.Notification;

import java.util.List;

public interface NotificationService {

    void inserNotification(Notification notification);

    List<Notification> queryNotification(long id);
}
