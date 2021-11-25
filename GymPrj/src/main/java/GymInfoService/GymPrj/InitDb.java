package GymInfoService.GymPrj;

import GymInfoService.GymPrj.domain.category.model.CityCategory;
import GymInfoService.GymPrj.domain.category.model.MemberTypeCategory;
import GymInfoService.GymPrj.domain.category.model.TownCategory;
import GymInfoService.GymPrj.domain.gym.model.Gym;
import GymInfoService.GymPrj.domain.gym.model.Trainer;
import GymInfoService.GymPrj.domain.gym.model.TrainerComment;
import GymInfoService.GymPrj.domain.member.model.Member;
import GymInfoService.GymPrj.domain.member.model.object.Address;
import GymInfoService.GymPrj.domain.member.model.object.Sex;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import static GymInfoService.GymPrj.domain.member.model.object.Sex.MALE;

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
                    .sex(MALE)
                    .address(new Address("서울특별시", "강서구"))
                    .phone("010-1111-2222")
                    .build();

            Member member1 = Member.builder()
                    .email("yuntae@naver.com")
                    .password(passwordEncoder.encode("qwe111"))
                    .name("박윤태")
                    .age(27)
                    .sex(MALE)
                    .address(new Address("부산시", "수영구"))
                    .phone("010-4444-5555")
                    .build();

            Member member2 = Member.builder()
                    .email("hyukjin@naver.com")
                    .password(passwordEncoder.encode("aaazzz111"))
                    .name("손혁진")
                    .age(27)
                    .sex(MALE)
                    .address(new Address("부산시", "수영구"))
                    .phone("010-7777-8888")
                    .build();

            em.persist(member);
            em.persist(member1);
            em.persist(member2);

            MemberTypeCategory normalCategory = MemberTypeCategory.builder()
                    .typeName("NORMAL")
                    .build();

            MemberTypeCategory gymCategory = MemberTypeCategory.builder()
                    .typeName("GYM")
                    .build();

            em.persist(normalCategory);
            em.persist(gymCategory);

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

            Gym gym = Gym.builder()
                    .cityId(1L)
                    .townId(1L)
                    .email("asdr1234@naver.com")
                    .password(passwordEncoder.encode("qwer1234"))
                    .gymName("주화짐")
                    .ceoName("정주화")
                    .tel("02-6666-7777")
                    .businessNumber("111-2222-3333")
                    .build();

            Gym gym1 = Gym.builder()
                    .cityId(1L)
                    .townId(2L)
                    .email("juhwa@naver.com")
                    .password(passwordEncoder.encode("qwer12345"))
                    .gymName("주화짐2")
                    .ceoName("정주화")
                    .tel("02-6666-1111")
                    .businessNumber("222-3333-4444")
                    .build();
            gym1.acceptGym();
            em.persist(gym);
            em.persist(gym1);

            Trainer trainer = Trainer.builder()
                    .name("정주화")
                    .age(26)
                    .sex(MALE)
                    .career(1)
                    .build();

            trainer.mapGym(gym1);
            em.persist(trainer);

            TrainerComment trainerComment = TrainerComment.builder()
                    .content("수업 굿굿!")
                    .build();

            trainerComment.mapTrainer(trainer);
            trainerComment.mapWriterId(2L);
            em.persist(trainerComment);

            member1.mapTrainerId(1L);


        }
    }
}
