package GymInfoService.GymPrj.domain.gym;

import GymInfoService.GymPrj.common.jwt.annotation.Authenticated;
import GymInfoService.GymPrj.common.jwt.annotation.JwtClaim;
import GymInfoService.GymPrj.domain.gym.dto.TrainerForm;
import GymInfoService.GymPrj.domain.gym.service.TrainerService;
import GymInfoService.GymPrj.domain.gym.service.query.TrainerQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    private final TrainerQueryService trainerQueryService;

    public TrainerController(TrainerService trainerService, TrainerQueryService trainerQueryService) {
        this.trainerService = trainerService;
        this.trainerQueryService = trainerQueryService;
    }

    @PostMapping
    @Authenticated
    public ResponseEntity<?> registerTrainer(@JwtClaim("info.id") Long gymId, @RequestBody TrainerForm trainerForm){

        Long trainerId = trainerService.registerTrainer(gymId, trainerForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(trainerId);
    }

    @GetMapping("/gym/{gymId}")
    public ResponseEntity<?> findTrainerByGymId(@PathVariable Long gymId){

        return ResponseEntity.ok(trainerQueryService.findTrainerByGymId(gymId));
    }

    @PutMapping("/{trainerId}")
    public ResponseEntity<?> updateTrainer(@JwtClaim("info.id") Long gymId, @PathVariable Long trainerId, @RequestBody TrainerForm trainerForm){
        trainerService.updateTrainer(gymId, trainerId, trainerForm);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping(("/{trainerId}"))
    public ResponseEntity<?> deleteTrainer(@JwtClaim("info.id") Long gymId, @PathVariable Long trainerId){
        trainerService.deleteTrainer(gymId,trainerId);
        return ResponseEntity.ok().build();
    }

}
