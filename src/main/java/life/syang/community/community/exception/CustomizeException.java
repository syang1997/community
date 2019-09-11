package life.syang.community.community.exception;

public class CustomizeException extends RuntimeException{
    private String message;
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.gerMessage();
    }

    public CustomizeException(String message){
        this.message=message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
