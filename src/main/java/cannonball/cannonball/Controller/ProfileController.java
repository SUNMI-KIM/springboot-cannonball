package cannonball.cannonball.Controller;

import cannonball.cannonball.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @PostMapping("/cannonball/join")
    public int join (@RequestParam int classNum, @RequestParam String name, @RequestParam String gender,
                     @RequestParam String password, @RequestParam String phoneNum, @RequestParam String className){
        return 1;
    }

    @PostMapping("cannonball/login")
    public int login (@RequestParam int classNum, @RequestParam String password){

        return profileService.MembershipLogin(classNum, password);
    }

    @DeleteMapping("cannonball/withdraw")
    public int withdraw (@RequestParam int classNum){
        return 1;
    }
}
