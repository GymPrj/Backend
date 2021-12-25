package GymInfoService.GymPrj.domain.chat.model;

import GymInfoService.GymPrj.common.base.Base;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class ChattingRoom extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatting_room_id")
    private Long id;

    private Long memberId;

    private Long gymId;

    private String title;

    @Builder
    public ChattingRoom(Long id, Long memberId, Long gymId, String title) {
        this.id = id;
        this.memberId = memberId;
        this.gymId = gymId;
        this.title = title;
    }

    public Long id(){return id;}
}
