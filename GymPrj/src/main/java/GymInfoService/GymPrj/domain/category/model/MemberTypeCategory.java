package GymInfoService.GymPrj.domain.category.model;

import GymInfoService.GymPrj.common.base.Base;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class MemberTypeCategory extends Base {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_type_category_id")
    private Long id;

    private String typeName;

    @Builder
    public MemberTypeCategory(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }
}
