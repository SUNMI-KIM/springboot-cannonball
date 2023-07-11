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
    public int join (Profile profile){
        return profileService.MembershipJoin(profile);
    }

    @PostMapping("/cannonball/login")
    public int login (@RequestBody Map<String, String> payload){
        String classNum = payload.get("classNum");
        String password = payload.get("password");
        return profileService.MembershipLogin(Integer.parseInt(classNum), password);
    }

    @DeleteMapping("/cannonball/withdraw")
    public int withdraw (@RequestBody String classNum){
        return profileService.MembershipWithDraw(Integer.parseInt(classNum));
    }

    @PostMapping("/cannonball/all")
    public List<Profile> showAllMember(){
        return profileService.allMember();
    }
}
