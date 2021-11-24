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
    GYM_PENDING_STATUS(HttpStatus.UNAUTHORIZED,"가입대기중인 아이디 입니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBERTYPE_NOT_FOUNT(HttpStatus.NOT_FOUND,"회원유형을 찾을 수 없습니다."),
    CITY_NOT_FOUNT(HttpStatus.NOT_FOUND,"도시를 찾을 수 없습니다."),
    TOWN_NOT_FOUNT(HttpStatus.NOT_FOUND,"구/군을 찾을 수 없습니다."),
    Gym_NOT_FOUND(HttpStatus.NOT_FOUND,"헬스장계정을 찾을 수 없습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_EMAIL(HttpStatus.CONFLICT,"이미 존재하는 이메일입니다."),

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
