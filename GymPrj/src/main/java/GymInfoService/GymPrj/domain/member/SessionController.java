package GymInfoService.GymPrj.domain.member;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final static int duration = 60 * 60 * 24;

    private final static String JWT_COOKIE_NAME = "gym.prj";

    private final SessionService sessionService;

    public SessionApiController(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
