package GymInfoService.GymPrj.domain.gym.model;

import GymInfoService.GymPrj.common.base.Base;
import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.domain.gym.dto.TrainerForm;
import GymInfoService.GymPrj.domain.member.model.object.Sex;
import lombok.Builder;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.*;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Trainer extends Base {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "trainer_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    private String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Integer age;

    private Integer career;

    @Builder
    public Trainer(Long id, Gym gym, String name, Sex sex, Integer age, Integer career) {
        this.id = id;
        this.gym = gym;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.career = career;
    }

    public void mapGym(Gym gym){
        this.gym = gym;
    }

    public Long id(){return id;}

    public void update(TrainerForm trainerForm){
        this.name = trainerForm.getName();
        this.sex = trainerForm.getSex();
        this.age = trainerForm.getAge();;
        this.career = trainerForm.getCareer();
    }

    public void checkGymId(Long gymId){
        if(this.gym.id() != gymId){
            throw new GymPrjException(ErrorCode.NOT_BELONG_GYM_TRAINER);
        }
    }

}
