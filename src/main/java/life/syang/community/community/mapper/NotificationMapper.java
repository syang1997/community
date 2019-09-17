package life.syang.community.community.mapper;

import life.syang.community.community.model.Notification;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {

    @Insert("insert  into notification(notifier,receiver,outer_id,type,gmt_create,status) values(#{notifier.id},#{receiver.id},#{outerId},#{type},#{gmtCreate},#{status})")
    void insertNotification(Notification notification);

    @Select("select count(1) from notification where status=0 and receiver_id=#{id}")
    int queryUnreadCount(@Param("id") long id);


    @Results(id ="nomap" ,value = {
            @Result(column = "notifier",property = "notifier",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")),
            @Result(column = "receiver",property = "receiver",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")),
            @Result(column = "outer_id",property = "outerId",one = @One(select = "life.syang.community.community.mapper.QuestionMapper.queryByCreater"))
    })
    @Select("select * from notification where type=1 and receiver=#{id}")
    List<Notification> queryNotificationWhenQuetion(@Param("id") long id);

    @Results(id ="nomaptwo" ,value = {
            @Result(column = "notifier",property = "notifier",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")),
            @Result(column = "receiver",property = "receiver",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")),
            @Result(column = "outer_id",property = "outerId",one = @One(select = "life.syang.community.community.mapper.CommentMapper.queryByCreater"))
    })
    @Select("select * from notification where type=2 and receiver=#{id}")
    List<Notification> queryNotificationWhenComment(@Param("id") long id);
}
