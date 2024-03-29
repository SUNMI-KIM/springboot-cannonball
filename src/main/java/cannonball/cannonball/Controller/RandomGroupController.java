package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.RandomGroup;
import cannonball.cannonball.DTO.Response;
import cannonball.cannonball.DTO.ResponseList;
import cannonball.cannonball.Service.RandomGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RandomGroupController {
    private final RandomGroupService randomGroupService;

    @Autowired
    public RandomGroupController(RandomGroupService randomGroupService){
        this.randomGroupService = randomGroupService;
    }

    @PostMapping("cannonball/random-group")
    public ResponseEntity<Response> makeRandomGroup(@RequestBody RandomGroup randomGroup){
        if (randomGroupService.makeRandom(randomGroup)) {
            return ResponseEntity.ok().body(new Response("랜덤 조 생성 성공", 1));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("랜덤 조 생성 실패", 0));
    }

    @GetMapping("cannonball/random-group")
    public ResponseEntity<ResponseList> showAllRandomGroup() {
        List<RandomGroup> randomGroups = randomGroupService.showAllRandom();
        return ResponseEntity.ok().body(new ResponseList("모든 랜덤 그룹 정보", randomGroups, randomGroups.size()));
    }

    @DeleteMapping("cannonball/random-group")
    public ResponseEntity<Response> deleteRandomGroup(@RequestBody Map<String, String> randomNameMap){
        String randomName = randomNameMap.get("randomName");
        if (randomGroupService.deleteRandom(randomName)) {
            return ResponseEntity.ok().body(new Response("랜덤 조 제거 성공", 1));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("랜덤 조 제거 실패", 0));
    }

    @PutMapping("cannonball/random-group")
    public ResponseEntity<Response> modifyRandomGroup(@RequestBody RandomGroup randomGroup) {
        if (randomGroupService.modifyRandom(randomGroup)) {
            return ResponseEntity.ok().body(new Response("랜덤 번개조 수정 성공", 1));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("랜덤 조 수정 실패", 0));
    }

    @GetMapping("cannonball/date")
    public ResponseEntity<Response> findStartRandomDate(@RequestParam("randomName") String randomName) {
        Response response = randomGroupService.findRandomDate(randomName);
        if (response.getData().equals(0)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok().body(response);
    }

}
