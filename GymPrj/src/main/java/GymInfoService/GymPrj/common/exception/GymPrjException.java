package GymInfoService.GymPrj.common.exception;


import org.springframework.http.HttpStatus;

public class GymPrjException extends RuntimeException{

    private final ErrorCode errorCode;

    public GymPrjException(ErrorCode errorCode) {
        super(errorCode.detail());
        this.errorCode = errorCode;
    }

    public ErrorCode errorCode(){
        return this.errorCode;
    }

    public HttpStatus status(){
        return errorCode.httpStatus();
    }
}
