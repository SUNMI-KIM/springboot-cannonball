package cannonball.cannonball.Controller;

import cannonball.cannonball.DTO.RandomApplicationDto;
import cannonball.cannonball.DTO.Response;
import cannonball.cannonball.DTO.ResponseList;
import cannonball.cannonball.Domain.RandomGroupApplication;
import cannonball.cannonball.Service.RandomGroupApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RandomGroupApplicationController {
    RandomGroupApplicationService randomGroupApplicationService;

    @Autowired
    public RandomGroupApplicationController(RandomGroupApplicationService randomGroupApplicationService){
        this.randomGroupApplicationService = randomGroupApplicationService;
    }

    @PostMapping("cannonball/random-group-application")
    public ResponseEntity<Response> groupApplication(@RequestBody RandomGroupApplication randomGroupApplication) {
        if (randomGroupApplicationService.application(randomGroupApplication)) {
            return ResponseEntity.ok().body(new Response("랜덤 조 신청 성공", 1));
        }
        return ResponseEntity.badRequest().body(new Response("랜덤 조 신청 실패", 0));
    }

    @DeleteMapping("cannonball/random-group-application")
    public ResponseEntity<Response> groupWithdraw(@RequestBody String classNum, String randomName) {
        if (randomGroupApplicationService.withdraw(randomName, classNum)) {
            return ResponseEntity.ok().body(new Response("랜덤 조 탈퇴 성공", 1));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("랜덤 조 탈퇴 실패", 0));
    }

    @GetMapping("cannonball/random-group-application")
    public ResponseEntity<ResponseList> showOrganizationGroup(@RequestBody Map<String, String> randomNameMap) {
        String randomName = randomNameMap.get("randomName");
        List<RandomApplicationDto> randomApplicationDtoList = randomGroupApplicationService.organizationShow(randomName);
        if (randomApplicationDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseList("랜덤 번개조를 찾을 수 없음", null, 0));
        }
        return ResponseEntity.ok().body(new ResponseList("랜덤번개조 편성 리스트", randomApplicationDtoList, randomApplicationDtoList.size()));
    }

    @GetMapping("cannonball/number-of-applicants")
    public ResponseEntity<Response> numberOfApplicants(@RequestBody Map<String, String> randomNameMap) {
        String randomName = randomNameMap.get("randomName");
        int applicants = randomGroupApplicationService.countOfApplicants(randomName);
        if (applicants > 0) return ResponseEntity.ok().body(new Response("랜덤 조 신청 인원", applicants));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("랜덤 조 신청 인원 불러오기 실패", 0));
    }

    @PostMapping("cannonball/organization-group")
    public ResponseEntity<Response> organizationGroup(@RequestBody Map<String, String> randomNameMap) {
        String randomName = randomNameMap.get("randomName");
        if (randomGroupApplicationService.groupOrganization(randomName)) {
            return ResponseEntity.ok().body(new Response("랜덤 조 편성 성공", 1));
        }
        return ResponseEntity.internalServerError().body(new Response("랜덤 조 편성 실패", 0));
    }


}
