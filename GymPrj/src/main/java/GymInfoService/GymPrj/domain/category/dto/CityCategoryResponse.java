package GymInfoService.GymPrj.domain.category.dto;

import GymInfoService.GymPrj.domain.category.model.CityCategory;
import lombok.Data;

@Data
public class CityCategoryResponse {

    private Long id;

    private String city;

    public CityCategoryResponse(CityCategory c) {
        this.id = c.getId();
        this.city = c.getCityName();
    }
}
