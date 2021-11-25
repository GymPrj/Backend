package GymInfoService.GymPrj.domain.gym;

import GymInfoService.GymPrj.common.jwt.annotation.Authenticated;
import GymInfoService.GymPrj.common.jwt.annotation.JwtClaim;
import GymInfoService.GymPrj.domain.gym.dto.TrainerCommentForm;
import GymInfoService.GymPrj.domain.gym.service.query.TrainerCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class TrainerCommentController {

    private final TrainerCommentService trainerCommentService;

    public TrainerCommentController(TrainerCommentService trainerCommentService) {
        this.trainerCommentService = trainerCommentService;
    }

    @PostMapping("/trainer/{trainerId}")
    @Authenticated
    public ResponseEntity<?> registerTrainerComment(@JwtClaim("info.id")Long memberId, @PathVariable Long trainerId, @RequestBody TrainerCommentForm trainerCommentForm){

        Long trainerCommentId = trainerCommentService.registerTrainerComment(memberId, trainerId, trainerCommentForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(trainerCommentId);
    }
}
