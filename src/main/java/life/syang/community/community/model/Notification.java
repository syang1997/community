package life.syang.community.community.model;

import lombok.Data;

@Data
public class Notification {
    private long id;
    private User notifier;
    private User receiver;
    private long outerId;//就是questionid在页面中做跳转用
    private String title;
    private int type;
    private long gmtCreate;
    private int status;
}
