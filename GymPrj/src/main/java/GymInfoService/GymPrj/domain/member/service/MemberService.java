package GymInfoService.GymPrj.domain.member.service;

import GymInfoService.GymPrj.domain.category.repository.MemberTypeCategoryRepository;
import GymInfoService.GymPrj.domain.member.dto.MemberForm;
import GymInfoService.GymPrj.domain.member.model.Member;
import GymInfoService.GymPrj.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final MemberValidator memberValidator;

    private final MemberTypeCategoryRepository memberTypeCategoryRepository;


    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, MemberValidator memberValidator, MemberTypeCategoryRepository memberTypeCategoryRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberValidator = memberValidator;
        this.memberTypeCategoryRepository = memberTypeCategoryRepository;
    }

    @Transactional
    public void join(MemberForm memberForm){

        Member member = memberForm.entity(memberValidator, passwordEncoder);


        memberRepository.save(member);
    }
}
