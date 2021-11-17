package GymInfoService.GymPrj.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "토큰 정보가 유효하지 않습니다"),
    TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다"),
    CREATE_TOKEN_FAIL(HttpStatus.BAD_REQUEST,"토큰 생성에 실패했습니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    MEMBER_NOT_FOUND(HttpStatus.UNAUTHORIZED,"존재하지 않는 회원 입니다."),
    NOT_EQUAL_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */

    /* 500 Internal Server Error : 서버 내부 문제 */
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부에 문제가 발생했습니다"),
    JWT_ALGORITHM_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR,"알고리즘이 존재하지 않습니다."),
    JSON_PROCESSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "JSON 파싱에 실패했습니다");

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
