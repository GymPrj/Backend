package GymInfoService.GymPrj.domain.member.service;

import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.common.jwt.JwtService;
import GymInfoService.GymPrj.domain.gym.model.Gym;
import GymInfoService.GymPrj.domain.gym.repository.GymRepository;
import GymInfoService.GymPrj.domain.member.dto.LoginForm;
import GymInfoService.GymPrj.domain.member.model.Member;
import GymInfoService.GymPrj.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class SessionService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final GymRepository gymRepository;

    public SessionService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtService jwtService, GymRepository gymRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.gymRepository = gymRepository;
    }

    public String login(LoginForm loginForm, int duration){

        Long loginTypeId = loginForm.getLoginTypeId();

        if(loginTypeCheck(loginTypeId)){
            Member member = memberRepository.findByEmail(loginForm.getEmail()).orElseThrow(() -> new GymPrjException(ErrorCode.MEMBER_NOT_FOUND));
            member.checkPassword(loginForm.getPassword(), passwordEncoder);
            return jwtService.createToken(member.createPayload(), ZonedDateTime.now().plusSeconds(duration));
        }else{
            Gym gym = gymRepository.findByEmail(loginForm.getEmail()).orElseThrow(() -> new GymPrjException(ErrorCode.Gym_NOT_FOUND));
            gym.checkPassword(loginForm.getPassword(), passwordEncoder);
            if(gym.checkJoinStatus()){
                throw new GymPrjException(ErrorCode.GYM_PENDING_STATUS);
            }
            return jwtService.createToken(gym.createPayload(), ZonedDateTime.now().plusSeconds(duration));
        }

    }

    private boolean loginTypeCheck(Long loginTypeId) {
        return loginTypeId == 1;
    }
}
