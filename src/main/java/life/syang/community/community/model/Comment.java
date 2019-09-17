package life.syang.community.community.model;

import lombok.Data;

/**
 *parentId:当评论为一级时,存放question的id
 *          二级时,为一级评论的id
 *          三级时,为接受方user的id
 *          type:1一级
 *               2二级
 *               3三级
 */
@Data
public class Comment {
    private long id;
    private long parentId;
    private int type;
    private User creator;
    private long gmtCreate;
    private long gmtModified;
    private long likeCount;
    private String content;
    private int replyCount;
}
