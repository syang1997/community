package life.syang.community.community.exception;

public enum  CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("问题沉到海底了!换一个吧!"),USER_NOT_LOGIN("先上船再出发!!!");
    CustomizeErrorCode(String meassage) {
        this.meassage = meassage;
    }

    private String meassage;

    @Override
    public String gerMessage() {
        return meassage;
    }
}
