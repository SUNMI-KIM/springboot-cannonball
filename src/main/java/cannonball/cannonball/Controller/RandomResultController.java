package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.RandomResult;
import cannonball.cannonball.Service.RandomResultService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public int makeRandomGroup(@RequestBody Map<String, String> randomNameMap){
        String randomName = randomNameMap.get("randomName");
        return randomResultService.makeRandomGroup(randomName);
    }

    @GetMapping("cannonball/show-group")
    public List<RandomResult> showAllRandomGroup(@RequestBody Map<String, String> randomNameMap){
        String randomName = randomNameMap.get("randomName");
        return randomResultService.showAll(randomName);
    }

    @PutMapping("cannonball/modify-group")
    public int modifyRandomGroupResult(@RequestBody List<RandomResult> randomResults){
        return randomResultService.modifyGroup(randomResults);
    }
}
