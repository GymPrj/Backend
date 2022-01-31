package GymInfoService.GymPrj.domain.admin;

import GymInfoService.GymPrj.common.jwt.annotation.Authenticated;
import GymInfoService.GymPrj.common.jwt.annotation.JwtClaim;
import GymInfoService.GymPrj.domain.admin.service.query.AdminQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminQueryService adminQueryService;

    public AdminController(AdminQueryService adminQueryService) {
        this.adminQueryService = adminQueryService;
    }

    @GetMapping("/findGymPending")
    @Authenticated
    public ResponseEntity<?> findGymPendingStatus(@JwtClaim("info.id") Long memeberId, Pageable pageable){

        return ResponseEntity.ok(adminQueryService.findGymPendingStatus(memeberId,pageable));
    }

    @PostMapping("/acceptGym/{gymId}")
    @Authenticated
    public ResponseEntity<?> acceptGym(@JwtClaim("info.id") Long memeberId, @PathVariable Long gymId){
        adminQueryService.acceptGym(memeberId, gymId);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
