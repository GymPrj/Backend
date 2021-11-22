package GymInfoService.GymPrj.domain.category.model;

import GymInfoService.GymPrj.common.base.Base;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class CityCategory extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_category_id")
    private Long id;

    private String cityName;

    @Builder
    public CityCategory(Long id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }
}
