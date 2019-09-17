package life.syang.community.community.enums;

public enum NotificationStatusEnum {
    UNREAD(0,"没有阅读"),
    READ(1,"已经阅读");
    private int status;
    private String name;

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    NotificationStatusEnum(int status, String name) {
        this.status = status;
        this.name = name;
    }
}
