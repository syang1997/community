package life.syang.community.community.model;

import lombok.Data;

@Data
public class BaseInfo {
    private Integer code;
    private String prompt;
    private Object data;
    public static BaseInfo successInfo(String prompt,Object data){
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setPrompt(prompt);
        baseInfo.setData(data);
        baseInfo.setCode(0);
        return baseInfo;
    }
    public static BaseInfo failInfo(String prompt,Object data){
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setPrompt(prompt);
        baseInfo.setData(data);
        baseInfo.setCode(0);
        return baseInfo;
    }
}
