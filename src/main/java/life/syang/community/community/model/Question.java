package life.syang.community.community.model;

import lombok.Data;

@Data
public class Question {
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private int creator;
    private int commentCount;
    private int viewCount;
    private String tag;
}
