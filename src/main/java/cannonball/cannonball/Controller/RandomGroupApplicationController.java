package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.RandomGroupApplication;
import cannonball.cannonball.Service.RandomGroupApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomGroupApplicationController {
    RandomGroupApplicationService randomGroupApplicationService;

    @Autowired
    public RandomGroupApplicationController(RandomGroupApplicationService randomGroupApplicationService){
        this.randomGroupApplicationService = randomGroupApplicationService;
    }

    @PostMapping("cannonball/application")
    public int GroupApplication(@RequestParam int classNum,
                                @RequestParam String name,
                                @RequestParam String gender,
                                @RequestParam String randomName) {
        RandomGroupApplication randomGroupApplication = new RandomGroupApplication();
        randomGroupApplication.setClassNum(classNum);
        randomGroupApplication.setName(name);
        randomGroupApplication.setGender(gender);
        randomGroupApplication.setRandomName(randomName);
        return randomGroupApplicationService.saveRandomApply(randomGroupApplication);
    }

    @DeleteMapping("cannonball/withdrawRandom")
    public int withdrawRandomGroupApplication(@RequestParam int classNum, @RequestParam String randomName){
        return randomGroupApplicationService.withdrawRandomApply(classNum, randomName);
    }

    @PostMapping("cannonball/numberOfApplicants")
    public int numberOfApplicants(@RequestParam String randomName){
        return randomGroupApplicationService.countOfApplicants(randomName);
    }
}
