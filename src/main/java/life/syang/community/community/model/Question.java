package life.syang.community.community.model;

import lombok.Data;

@Data
public class Question {
    private long id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private User creator;//发布人
    private int commentCount;
    private int viewCount;
    private String tag;
}
