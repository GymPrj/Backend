package GymInfoService.GymPrj.domain.gym.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrainerCommentResponse {

    private final String writerName;

    private final String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDateTime createdDate;

    public TrainerCommentResponse(String writerName, String content, LocalDateTime createdDate) {
        this.writerName = writerName;
        this.content = content;
        this.createdDate = createdDate;
    }
}
