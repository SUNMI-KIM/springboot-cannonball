package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.RandomGroupApplication;
import cannonball.cannonball.Service.RandomGroupApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RandomGroupApplicationController {
    RandomGroupApplicationService randomGroupApplicationService;

    @Autowired
    public RandomGroupApplicationController(RandomGroupApplicationService randomGroupApplicationService){
        this.randomGroupApplicationService = randomGroupApplicationService;
    }

    @PostMapping("cannonball/application")
    public int GroupApplication(@RequestBody RandomGroupApplication randomGroupApplication) {
        return randomGroupApplicationService.saveRandomApply(randomGroupApplication);
    }

    @DeleteMapping("cannonball/withdrawRandom")
    public int withdrawRandomGroupApplication(@RequestBody Map<String, String> payload){
        String classNumString = payload.get("classNum");
        if (classNumString == null) {
            return 0;
        }
        try {
            int i = Integer.parseInt(classNumString);
        } catch (NumberFormatException nfe) {
            return 0;
        }
        int classNumInt = Integer.parseInt(classNumString);
        String randomName = payload.get("randomName");
        return randomGroupApplicationService.withdrawRandomApply(classNumInt, randomName);
    }

    @PostMapping("cannonball/numberOfApplicants")
    public int numberOfApplicants(@RequestBody String randomName){
        return randomGroupApplicationService.countOfApplicants(randomName);
    }
}
