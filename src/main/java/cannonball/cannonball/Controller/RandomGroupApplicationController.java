package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.RandomGroupApplication;
import cannonball.cannonball.Domain.Response;
import cannonball.cannonball.Service.RandomGroupApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RandomGroupApplicationController {
    RandomGroupApplicationService randomGroupApplicationService;

    @Autowired
    public RandomGroupApplicationController(RandomGroupApplicationService randomGroupApplicationService){
        this.randomGroupApplicationService = randomGroupApplicationService;
    }

    @PostMapping("cannonball/random-group-application")
    public ResponseEntity<Response> GroupApplication(@RequestBody RandomGroupApplication randomGroupApplication) {
        if (randomGroupApplicationService.saveRandomApply(randomGroupApplication)) {
            return ResponseEntity.ok().body(new Response("랜덤 번개조 신청 성공", 1));
        }
        return ResponseEntity.badRequest().body(new Response("랜덤 번개 조 신청 실패", 0));
    }

    @DeleteMapping("cannonball/random-group-application")
    public ResponseEntity<Response> withdrawRandomGroupApplication(@RequestBody Map<String, String> payload){
        String classNum = payload.get("classNum");
        String randomName = payload.get("randomName");
        if (randomGroupApplicationService.withdrawRandomApply(classNum, randomName)) {
            return ResponseEntity.ok().body(new Response("랜덤 조 탈퇴 성공", 1));
        }
        return ResponseEntity.badRequest().body(new Response("랜덤 조 탈퇴 실패", 0));
    }

    @GetMapping("cannonball/number-of-applicants")
    public ResponseEntity<Response> numberOfApplicants(@RequestBody Map<String, String> RandomNameMap){
        String randomName = RandomNameMap.get("randomName");
        int count = randomGroupApplicationService.countOfApplicants(randomName);
        if (count > 0) {
            return ResponseEntity.ok().body(new Response("랜덤 조 신청 인원", count));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("랜덤 조를 찾을 수 없음", 0));
    }
}
