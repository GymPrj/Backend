package GymInfoService.GymPrj.domain.gym.model;

import GymInfoService.GymPrj.common.base.Base;
import lombok.Builder;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class TrainerComment extends Base {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "trainer_comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    private Long writerId;

    private String content;

    @Builder
    public TrainerComment(Long id, Trainer trainer, Long writerId, String content) {
        this.id = id;
        this.trainer = trainer;
        this.writerId = writerId;
        this.content = content;
    }
}
