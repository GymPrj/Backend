package GymInfoService.GymPrj.domain.member.dto;

import GymInfoService.GymPrj.domain.member.model.Member;
import GymInfoService.GymPrj.domain.member.model.object.Address;
import GymInfoService.GymPrj.domain.member.model.object.Sex;
import GymInfoService.GymPrj.domain.member.service.MemberValidator;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class MemberForm {

    private Long memberTypeId;

    private String email;

    private String password;

    private String name;

    private Sex sex;

    private Integer age;

    private String city;

    private String town;

    private String phone;


    public Member entity(MemberValidator memberValidator, PasswordEncoder passwordEncoder){
        encryptPassword(passwordEncoder);
        validateEmail(memberValidator);
        return Member.builder()
                .memberTypeId(memberTypeId)
                .email(email)
                .password(password)
                .name(name)
                .sex(sex)
                .age(age)
                .address(new Address(city,town))
                .phone(phone)
                .build();
    }

    private void validateEmail(MemberValidator memberValidator) {
        memberValidator.validateEmail(email);
    }

    private void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }


}
