package GymInfoService.GymPrj.domain.gym.dto;

import GymInfoService.GymPrj.domain.gym.model.Trainer;
import GymInfoService.GymPrj.domain.member.model.object.Sex;
import lombok.Data;

@Data
public class TrainerForm {

    private String name;

    private Sex sex;

    private Integer age;

    private Integer career;

    public Trainer entity(){
        return Trainer.builder()
                .name(name)
                .sex(sex)
                .age(age)
                .career(career)
                .build();
    }


}
