package GymInfoService.GymPrj;

import GymInfoService.GymPrj.domain.member.model.Member;
import GymInfoService.GymPrj.domain.member.model.object.Address;
import GymInfoService.GymPrj.domain.member.model.object.Sex;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
    }

    @Component
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        private final PasswordEncoder passwordEncoder;

        @Transactional
        public void dbInit(){
            Member member = Member.builder()
                    .email("qwe199942@naver.com")
                    .password(passwordEncoder.encode("qwe123"))
                    .name("정주화")
                    .age(26)
                    .sex(Sex.MALE)
                    .address(new Address("서울특별시", "강서구"))
                    .build();

            em.persist(member);
        }
    }
}
