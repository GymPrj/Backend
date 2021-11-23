package GymInfoService.GymPrj.domain.member.model;

import GymInfoService.GymPrj.common.base.Base;
import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.common.jwt.MemberGymPayload;
import GymInfoService.GymPrj.domain.member.model.object.Address;
import GymInfoService.GymPrj.domain.member.model.object.Sex;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Member extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private Long memberTypeId;

    private Long gymId;

    private String email;

    private String password;

    private String name;

    @Enumerated(STRING)
    private Sex sex;

    private Integer age;
    
    private Address address;

    private String phone;


    @Builder
    public Member(Long id, String email, String password, String name, Sex sex, Integer age, Address address, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.memberTypeId = 1L;

    }




    public void checkPassword(String password, PasswordEncoder passwordEncoder){
        if(isNotEqualsPassword(password, passwordEncoder)){
            throw new GymPrjException(ErrorCode.NOT_EQUAL_PASSWORD);
        }
    }

    public void mapMemberType(Long memberTypeId){
        this.memberTypeId = memberTypeId;
    }

    private boolean isNotEqualsPassword(String password, PasswordEncoder passwordEncoder) {
        return !passwordEncoder.matches(password, this.password);
    }

    public MemberGymPayload createPayload(){return new MemberGymPayload(id, email);}
}
