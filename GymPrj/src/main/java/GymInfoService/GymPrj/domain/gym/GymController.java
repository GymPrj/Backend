package GymInfoService.GymPrj.domain.gym;

import GymInfoService.GymPrj.domain.gym.dto.GymForm;
import GymInfoService.GymPrj.domain.gym.service.GymService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gym")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinGym(@RequestBody GymForm gymForm) {

        gymService.join(gymForm);

        return ResponseEntity.ok(HttpStatus.CREATED);

    }
}
