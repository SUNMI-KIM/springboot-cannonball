package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.Profile;
import cannonball.cannonball.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @PostMapping("/cannonball/join")
    public int join (@RequestBody Profile profile){
        return profileService.MembershipJoin(profile);
    }

    @PostMapping("/cannonball/login")
    public int login (@RequestBody Map<String, String> payload){
        String classNum = payload.get("classNum");
        String password = payload.get("passWord");
        return profileService.MembershipLogin(classNum, password);
    }

    @DeleteMapping("/cannonball/withdraw")
    public int withdraw (@RequestBody Map<String, String> classNumMap){
        String classNum = classNumMap.get("classNum");
        if (classNum == null) {
            return 0;
        }
        return profileService.MembershipWithDraw(classNum);
    }

    @PostMapping("/cannonball/all")
    public List<Profile> showAllMember(){
        return profileService.allMember();
    }

}
