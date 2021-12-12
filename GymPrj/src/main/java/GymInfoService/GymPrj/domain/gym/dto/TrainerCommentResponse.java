package GymInfoService.GymPrj.domain.gym.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrainerCommentResponse {

    private final Long id;

    private final String writerName;

    private final String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDateTime createdDate;

    public TrainerCommentResponse(Long id, String writerName, String content, LocalDateTime createdDate) {
        this.id = id;
        this.writerName = writerName;
        this.content = content;
        this.createdDate = createdDate;
    }
}
