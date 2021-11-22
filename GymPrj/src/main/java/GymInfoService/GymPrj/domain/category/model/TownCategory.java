package GymInfoService.GymPrj.domain.category.model;

import GymInfoService.GymPrj.common.base.Base;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class TownCategory extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_category_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "city_category_id")
    private CityCategory category;

    private String townName;

    @Builder
    public TownCategory(Long id, CityCategory category, String townName) {
        this.id = id;
        this.category = category;
        this.townName = townName;
    }


}
