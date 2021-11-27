package GymInfoService.GymPrj.domain.gym.model;

import GymInfoService.GymPrj.common.base.Base;
import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import lombok.Builder;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
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

    public void mapWriterId(Long memberId) {
        this.writerId = memberId;
    }

    public void mapTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Long id() {
        return id;
    }

    public void checkWriterId(Long writerId){
        if(this.writerId != writerId){
            throw new GymPrjException(ErrorCode.NOT_WRITER_COMMENT);
        }
    }

    public void updateContent(String content){
        this.content = content;
    }

}
