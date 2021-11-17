package GymInfoService.GymPrj.domain.member.service;

import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.common.jwt.JwtService;
import GymInfoService.GymPrj.domain.member.dto.LoginForm;
import GymInfoService.GymPrj.domain.member.model.Member;
import GymInfoService.GymPrj.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public String login(LoginForm loginForm, int duration){
        Member member = memberRepository.findByEmail(loginForm.getEmail()).orElseThrow(() -> new GymPrjException(ErrorCode.MEMBER_NOT_FOUND));
        member.checkPassword(loginForm.getPassword(), passwordEncoder);
        return jwtService.createToken(member.createPayload(), ZonedDateTime.now().plusSeconds(duration));
    }
}
