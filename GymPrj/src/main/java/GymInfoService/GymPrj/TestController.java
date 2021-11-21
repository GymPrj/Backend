package GymInfoService.GymPrj;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){return "정신차려 kojeong, kyeongja, hyukjin, jeyeong";}
}
