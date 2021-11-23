package GymInfoService.GymPrj.domain.gym.dto;


import lombok.Data;

@Data
public class FindGymCondition {

    private final Long cityId;

    private final Long townId;

    private final String gymName;

}
