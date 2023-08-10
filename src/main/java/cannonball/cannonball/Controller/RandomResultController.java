package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.RandomResult;
import cannonball.cannonball.Domain.Response;
import cannonball.cannonball.Domain.ResponseList;
import cannonball.cannonball.Service.RandomResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RandomResultController {
    private final RandomResultService randomResultService;

    @Autowired
    public RandomResultController(RandomResultService randomResultService){
        this.randomResultService = randomResultService;
    }

    @PostMapping("cannonball/make-random")
    public ResponseEntity<Response> makeRandomGroup(@RequestBody Map<String, String> randomNameMap){
        String randomName = randomNameMap.get("randomName");
        if (randomResultService.makeRandomGroup(randomName)) {
            return ResponseEntity.ok().body(new Response("랜덤 조 편성 성공", 1));
        }
        return ResponseEntity.internalServerError().body(new Response("랜덤 조 편성 실패", 0));
    }

    @GetMapping("cannonball/show-group")
    public ResponseEntity<ResponseList> showAllRandomGroup(@RequestBody Map<String, String> randomNameMap){
        String randomName = randomNameMap.get("randomName");
        List<RandomResult> randomResults = randomResultService.showAll(randomName);
        if (randomResults.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseList("랜덤 조 편성을 먼저 진행 후 실행", null, 0));
        }
        return ResponseEntity.ok().body(new ResponseList("랜덤 조 편성 리스트", randomResults, randomResults.size()));
    }

    @PutMapping("cannonball/modify-group")
    public ResponseEntity<Response> modifyRandomGroupResult(@RequestBody RandomResult randomResult){
        if (randomResultService.modifyGroup(randomResult)) {
            return ResponseEntity.ok().body(new Response("랜덤 조 유저 정보 변경 성공", 1));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("랜덤 조 유저 정보 변경 실패", 0));
    }
}
