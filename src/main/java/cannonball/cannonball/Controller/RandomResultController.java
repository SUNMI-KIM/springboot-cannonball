package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.RandomResult;
import cannonball.cannonball.Service.RandomResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RandomResultController {
    private final RandomResultService randomResultService;

    @Autowired
    public RandomResultController(RandomResultService randomResultService){
        this.randomResultService = randomResultService;
    }

    @PostMapping("cannonball/make-random")
    public int makeRandomGroup(@RequestBody String randomName){
        return randomResultService.makeRandomGroup(randomName);
    }

    @PostMapping("cannonball/show-group")
    public List<RandomResult> showAllRandomGroup(@RequestBody String randomName){
        return randomResultService.showAll(randomName);
    }
}
