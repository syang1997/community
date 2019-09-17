package life.syang.community.community.model;

import lombok.Data;

@Data
public class Notification {
    private long id;
    private User notifier;
    private User receiver;
    private Object outerId;
    private int type;
    private long gmtCreate;
    private int status;
}
