package GymInfoService.GymPrj.domain.member;

import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.common.jwt.CookieFactory;
import GymInfoService.GymPrj.domain.member.dto.LoginForm;
import GymInfoService.GymPrj.domain.member.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final static int duration = 60 * 60 * 24;

    private final static String JWT_COOKIE_NAME = "gym.prj";

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        String jwtToken = sessionService.login(loginForm, duration);

        if(jwtToken.isBlank()){
            throw new GymPrjException(ErrorCode.CREATE_TOKEN_FAIL);
        }

        httpServletResponse.addCookie(createCookie(jwtToken,httpServletRequest));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);

    }

    private Cookie createCookie(String jwtToken, HttpServletRequest httpServletRequest) {
        return CookieFactory.createCookie(cookieConfig ->
                cookieConfig.name(JWT_COOKIE_NAME)
                        .value(jwtToken)
                        .expires(duration)
                        .secure("https".equals(httpServletRequest.getScheme()))
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        CookieFactory.getCookie(httpServletRequest, JWT_COOKIE_NAME)
                .ifPresent(cookie -> CookieFactory.removeCookie(cookie,httpServletResponse));

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
