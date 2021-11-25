package GymInfoService.GymPrj.domain.gym.dto;

import GymInfoService.GymPrj.domain.gym.model.TrainerComment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrainerCommentForm {

    private String content;

    public TrainerComment entity(){
        return TrainerComment.builder()
                .content(content)
                .build();
    }
}
