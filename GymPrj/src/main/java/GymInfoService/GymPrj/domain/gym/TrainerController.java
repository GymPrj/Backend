package GymInfoService.GymPrj.domain.gym;

import GymInfoService.GymPrj.common.jwt.annotation.Authenticated;
import GymInfoService.GymPrj.common.jwt.annotation.JwtClaim;
import GymInfoService.GymPrj.domain.gym.dto.TrainerForm;
import GymInfoService.GymPrj.domain.gym.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    @PostMapping
    @Authenticated
    public ResponseEntity<?> registerTrainer(@JwtClaim("info.id") Long gymId, @RequestBody TrainerForm trainerForm){
        Long trainerId = trainerService.registerTrainer(gymId, trainerForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainerId);
    }

}
