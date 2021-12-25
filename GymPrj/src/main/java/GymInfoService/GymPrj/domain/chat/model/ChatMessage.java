package GymInfoService.GymPrj.domain.chat.model;

import GymInfoService.GymPrj.common.base.Base;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class ChatMessage extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatting_message_id")
    private Long id;

    private String content;

    private Long writerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatting_room_id")
    private ChattingRoom chattingRoom;

    @Builder
    public ChatMessage(Long id, String content, Long writerId, ChattingRoom chattingRoom) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;
        this.chattingRoom = chattingRoom;
    }

    public Long id(){return id;}
}
