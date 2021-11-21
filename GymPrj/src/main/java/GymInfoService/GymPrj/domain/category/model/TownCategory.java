package GymInfoService.GymPrj.domain.category.model;

import GymInfoService.GymPrj.common.base.Base;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class TownCategory extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_category_id")
    private Long id;

    private String townName;

    @Builder
    public TownCategory(Long id, String townName) {
        this.id = id;
        this.townName = townName;
    }
}
