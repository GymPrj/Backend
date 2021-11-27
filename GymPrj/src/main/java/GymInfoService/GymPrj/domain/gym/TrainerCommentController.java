package GymInfoService.GymPrj.domain.gym;

import GymInfoService.GymPrj.common.jwt.annotation.Authenticated;
import GymInfoService.GymPrj.common.jwt.annotation.JwtClaim;
import GymInfoService.GymPrj.domain.gym.dto.TrainerCommentForm;
import GymInfoService.GymPrj.domain.gym.service.TrainerCommentService;
import GymInfoService.GymPrj.domain.gym.service.query.TrainerCommentQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class TrainerCommentController {

    private final TrainerCommentService trainerCommentService;

    private final TrainerCommentQueryService trainerCommentQueryService;

    public TrainerCommentController(TrainerCommentService trainerCommentService, TrainerCommentQueryService trainerCommentQueryService) {
        this.trainerCommentService = trainerCommentService;
        this.trainerCommentQueryService = trainerCommentQueryService;
    }

    @PostMapping("/trainer/{trainerId}")
    @Authenticated
    public ResponseEntity<?> registerTrainerComment(@JwtClaim("info.id")Long memberId, @PathVariable Long trainerId, @RequestBody TrainerCommentForm trainerCommentForm){

        Long trainerCommentId = trainerCommentService.registerTrainerComment(memberId, trainerId, trainerCommentForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(trainerCommentId);
    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<?> findTrainerComment(@PathVariable Long trainerId){
        return ResponseEntity.ok(trainerCommentQueryService.findTrainerComment(trainerId));
    }

    @PutMapping("/{trainerCommentId}/trainer")
    @Authenticated
    public ResponseEntity<?> updateTrainerComment(@JwtClaim("info.id")Long memberId, @PathVariable Long trainerCommentId, @RequestBody TrainerCommentForm trainerCommentForm){
        trainerCommentService.updateTrainerComment(memberId, trainerCommentId, trainerCommentForm);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("/{trainerCommentId}/trainer")
    @Authenticated
    public ResponseEntity<?> deleteTrainerComment(@JwtClaim("info.id")Long memberId, @PathVariable Long trainerCommentId){

        trainerCommentService.deleteTrainerComment(memberId,trainerCommentId);

        return ResponseEntity.ok().build();
    }

}
