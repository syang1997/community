package life.syang.community.community.mapper;

import life.syang.community.community.model.Notification;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {

    @Insert("insert  into notification(notifier,receiver,outer_id,type,gmt_create,status,title) values(#{notifier.id},#{receiver.id},#{outerId},#{type},#{gmtCreate},#{status},#{title})")
    void insertNotification(Notification notification);

    @Select("select count(1) from notification where status=0 and receiver=#{id}")
    int queryUnreadCount(@Param("id") long id);

    @Select("select * from notification where  id=#{id}")
    Notification queryNotifiactionByid(@Param("id") long id);

    @Results(id = "nomap", value = {
            @Result(column = "notifier", property = "notifier", one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")),
            @Result(column = "receiver", property = "receiver", one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")),
    })
    @Select("select * from notification where receiver=#{id} ORDER BY gmt_create DESC")
    List<Notification> queryNotificationWhenQuetion(@Param("id") long id);

    @Update("update notification set status=1 where id=#{id} and receiver=#{receiver}")
    int readOne(@Param("id")long id,@Param("receiver") long receiver);
}
