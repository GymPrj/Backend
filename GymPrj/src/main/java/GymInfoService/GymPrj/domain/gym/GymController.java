package GymInfoService.GymPrj.domain.gym;

import GymInfoService.GymPrj.domain.gym.dto.FindGymCondition;
import GymInfoService.GymPrj.domain.gym.dto.GymForm;
import GymInfoService.GymPrj.domain.gym.service.GymService;
import GymInfoService.GymPrj.domain.gym.service.query.GymQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gym")
public class GymController {

    private final GymService gymService;
    private final GymQueryService gymQueryService;

    public GymController(GymService gymService, GymQueryService gymQueryService) {
        this.gymService = gymService;
        this.gymQueryService = gymQueryService;
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinGym(@RequestBody GymForm gymForm) {

        gymService.join(gymForm);

        return ResponseEntity.ok(HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<?> findGymByCondition(FindGymCondition findGymCondition, Pageable pageable){

        return ResponseEntity.ok(gymQueryService.findGymByCondition(findGymCondition,pageable));
    }
}
