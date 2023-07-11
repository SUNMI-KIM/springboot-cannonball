package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.RandomGroup;
import cannonball.cannonball.Service.RandomGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class RandomGroupController {
    private final RandomGroupService randomGroupService;

    @Autowired
    public RandomGroupController(RandomGroupService randomGroupService){
        this.randomGroupService = randomGroupService;
    }

    @PostMapping("cannonball/makeRandom")
    public RandomGroup makeRandomGroup(@RequestBody RandomGroup randomGroup){
        return randomGroupService.makeRandom(randomGroup);
    }

    @PostMapping("cannonball/showAllRandomGroup")
    public List<RandomGroup> showAllRandomGroup() {
        return randomGroupService.showAllRandom();
    }

    @PostMapping("cannonball/deleteRandom")
    public int deleteRandomGroup(@RequestBody String randomName){
        return randomGroupService.deleteRandom(randomName);
    }
}
