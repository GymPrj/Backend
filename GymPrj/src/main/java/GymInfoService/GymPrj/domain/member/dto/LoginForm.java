package GymInfoService.GymPrj.domain.member.dto;

import lombok.Data;

@Data
public class LoginForm {

    private final String email;
    private final String password;

}
