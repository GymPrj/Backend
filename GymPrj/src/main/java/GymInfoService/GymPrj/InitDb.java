package GymInfoService.GymPrj;

import GymInfoService.GymPrj.domain.category.model.CityCategory;
import GymInfoService.GymPrj.domain.category.model.MemberTypeCategory;
import GymInfoService.GymPrj.domain.category.model.TownCategory;
import GymInfoService.GymPrj.domain.gym.model.Gym;
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
                    .phone("010-1111-2222")
                    .build();

            em.persist(member);

            MemberTypeCategory normal = MemberTypeCategory.builder()
                    .typeName("NORMAL")
                    .build();

            MemberTypeCategory gym = MemberTypeCategory.builder()
                    .typeName("GYM")
                    .build();

            em.persist(normal);
            em.persist(gym);

            CityCategory cityCategory = CityCategory.builder()
                    .cityName("서울시")
                    .build();
            CityCategory cityCategory1 = CityCategory.builder()
                    .cityName("부산시")
                    .build();

            TownCategory townCategory = TownCategory.builder()
                    .townName("강서구")
                    .category(cityCategory)
                    .build();
            TownCategory townCategory1 = TownCategory.builder()
                    .townName("강남구")
                    .category(cityCategory)
                    .build();

            TownCategory townCategory2 = TownCategory.builder()
                    .townName("수영구")
                    .category(cityCategory1)
                    .build();

            TownCategory townCategory3 = TownCategory.builder()
                    .townName("금정구")
                    .category(cityCategory1)
                    .build();

            em.persist(cityCategory);
            em.persist(townCategory);
            em.persist(cityCategory1);
            em.persist(townCategory1);
            em.persist(townCategory2);
            em.persist(townCategory3);

        }
    }
}
