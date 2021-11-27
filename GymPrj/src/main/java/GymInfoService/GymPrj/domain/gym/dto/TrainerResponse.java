package GymInfoService.GymPrj.domain.gym.dto;

import GymInfoService.GymPrj.domain.member.model.object.Sex;
import lombok.Data;

@Data
public class TrainerResponse {

    private final String name;

    private final Sex sex;

    private final Integer age;

    private final Integer career;

    public TrainerResponse(String name, Sex sex, Integer age, Integer career) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.career = career;
    }
}
