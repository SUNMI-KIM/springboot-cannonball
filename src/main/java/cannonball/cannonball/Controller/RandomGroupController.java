package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.RandomGroup;
import cannonball.cannonball.Service.RandomGroupService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("cannonball/makeRandom")
    public int makeRandomGroup(@RequestBody RandomGroup randomGroup){
        return randomGroupService.makeRandom(randomGroup);
    }

    @GetMapping("cannonball/showAllRandomGroup")
    public List<RandomGroup> showAllRandomGroup() {
        return randomGroupService.showAllRandom();
    }

    @DeleteMapping("cannonball/deleteRandom")
    public int deleteRandomGroup(@RequestBody Map<String, String> randomNameMap){
        String randomName = randomNameMap.get("randomName");
        return randomGroupService.deleteRandom(randomName);
    }
}
