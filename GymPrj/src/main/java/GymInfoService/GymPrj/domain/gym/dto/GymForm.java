package GymInfoService.GymPrj.domain.gym.dto;

import GymInfoService.GymPrj.domain.gym.model.Gym;
import GymInfoService.GymPrj.domain.gym.service.GymValidator;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class GymForm {

    private Long cityId;

    private Long townId;

    private String detailAddress;

    private String email;

    private String password;

    private String gymName;

    private String ceoName;

    private String businessNumber;

    private String tel;

    public Gym entity(GymValidator gymValidator, PasswordEncoder passwordEncoder){
        validateEmail(gymValidator);
        encryptPassword(passwordEncoder);
        return Gym.builder()
                .email(email)
                .password(password)
                .gymName(gymName)
                .ceoName(ceoName)
                .businessNumber(businessNumber)
                .tel(tel)
                .detailAddress(detailAddress)
                .build();
    }

    private void validateEmail(GymValidator gymValidator) {
        gymValidator.validateEmail(email);
    }

    private void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
