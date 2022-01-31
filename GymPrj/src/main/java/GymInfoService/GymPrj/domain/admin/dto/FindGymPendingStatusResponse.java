package GymInfoService.GymPrj.domain.admin.dto;

import lombok.Data;

@Data
public class FindGymPendingStatusResponse {

    private String city;

    private String town;

    private String detailAddress;

    private String gymName;

    private String ceoName;

    private String businessNumber;

    private String tel;

    public FindGymPendingStatusResponse(String city, String town, String detailAddress, String gymName, String ceoName, String businessNumber, String tel) {
        this.city = city;
        this.town = town;
        this.detailAddress = detailAddress;
        this.gymName = gymName;
        this.ceoName = ceoName;
        this.businessNumber = businessNumber;
        this.tel = tel;
    }


}
