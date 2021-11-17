package GymInfoService.GymPrj.fixture;

import GymInfoService.GymPrj.domain.member.model.Member;
import GymInfoService.GymPrj.domain.member.model.object.Address;
import GymInfoService.GymPrj.domain.member.model.object.Sex;

public class MemberFixture {

    public static Member.MemberBuilder member(){
        return Member.builder()
                .id(1L)
                .email("qwe199942@naver.com")
                .password("qwe123")
                .name("정주화")
                .age(26)
                .sex(Sex.MALE)
                .address(new Address("서울시","강서구"));
    }
}
