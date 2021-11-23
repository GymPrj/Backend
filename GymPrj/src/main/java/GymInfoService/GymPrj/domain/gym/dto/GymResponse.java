package GymInfoService.GymPrj.domain.gym.dto;

import lombok.Data;

@Data
public class GymResponse {

    private final String gymName;

    private final String ceoName;

    private final String tel;

    private final Long memberCount;

    public GymResponse(String gymName, String ceoName, String tel, Long memberCount) {
        this.gymName = gymName;
        this.ceoName = ceoName;
        this.tel = tel;
        this.memberCount = memberCount;
    }
}
