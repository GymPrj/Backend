package GymInfoService.GymPrj.domain.member.dto;

import lombok.Data;

@Data
public class LoginForm {

    private String email;
    private String password;
    private Long loginTypeId;

}
