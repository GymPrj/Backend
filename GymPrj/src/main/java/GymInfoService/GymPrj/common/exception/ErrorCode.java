package GymInfoService.GymPrj.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    /* 500 Internal Server Error : 서버 내부 문제 */
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부에 문제가 발생했습니다");

    private final HttpStatus httpStatus;
    private final String detail;

    ErrorCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }

    public String detail(){
        return this.detail;
    }

    public HttpStatus httpStatus(){
        return this.httpStatus;
    }
}
