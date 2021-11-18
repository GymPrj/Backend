package GymInfoService.GymPrj.domain.member.service;

import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberValidator {

    private final MemberRepository memberRepository;

    public MemberValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void validateEmail(String email){
        Long count = memberRepository.countByEmail(email);

        if(isPresent(count)){
            throw new GymPrjException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    private boolean isPresent(Long count) {
        return count > 0;
    }
}
