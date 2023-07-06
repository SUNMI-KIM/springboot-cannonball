package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.RandomGroup;
import cannonball.cannonball.Service.RandomGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public RandomGroup makeRandomGroup(@RequestParam String randomName, @RequestParam int boyGirlNum, @RequestParam Date deadLine,
                                  @RequestParam Date raiseRandom, @RequestParam int inGroupOf, @RequestParam Date startRandom){
        RandomGroup randomGroup = new RandomGroup();
        randomGroup.setRandomName(randomName);
        randomGroup.setBoyGirlNum(boyGirlNum);
        randomGroup.setDeadLine(deadLine);
        randomGroup.setRaiseRandom(raiseRandom);
        randomGroup.setInGroupOf(inGroupOf);
        randomGroup.setStartRandom(startRandom);
        return randomGroupService.makeRandom(randomGroup);
    }

    @PostMapping("cannonball/showAllRandomGroup")
    public List<RandomGroup> showAllRandomGroup() {
        return randomGroupService.showAllRandom();
    }

    @PostMapping("cannonball/deleteRandom")
    public int deleteRandomGroup(@RequestParam String randomName){
        return randomGroupService.deleteRandom(randomName);
    }
}
