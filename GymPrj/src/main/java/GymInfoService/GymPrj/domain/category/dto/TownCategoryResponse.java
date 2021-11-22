package GymInfoService.GymPrj.domain.category.dto;

import GymInfoService.GymPrj.domain.category.model.TownCategory;
import lombok.Data;

@Data
public class TownCategoryResponse {

    private Long townId;

    private String town;

    public TownCategoryResponse(Long townId, String town) {
        this.townId = townId;
        this.town = town;
    }
}
