package GymInfoService.GymPrj.domain.gym.dto;

import lombok.Data;

@Data
public class GymResponse {

    private final Long gymId;

    private final String gymName;

    private final String ceoName;

    private final String tel;

    private final Long memberCount;

    private final String detailAddress;

    public GymResponse(Long gymId, String gymName, String ceoName, String tel, Long memberCount, String detailAddress) {
        this.gymId = gymId;
        this.gymName = gymName;
        this.ceoName = ceoName;
        this.tel = tel;
        this.memberCount = memberCount;
        this.detailAddress = detailAddress;
    }

}
