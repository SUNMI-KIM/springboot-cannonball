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
        String classNumString = payload.get("classNum");
        if (classNumString == null) {
            return 0;
        }
        try {
            int i = Integer.parseInt(classNumString);
        } catch (NumberFormatException nfe) {
            return 0;
        }
        int classNum = Integer.parseInt(classNumString);
        String password = payload.get("password");
        return profileService.MembershipLogin(classNum, password);
    }

    @DeleteMapping("/cannonball/withdraw")
    public int withdraw (@RequestBody String classNum){
        if (classNum == null) {
            return 0;
        }
        try {
            int i = Integer.parseInt(classNum);
        } catch (NumberFormatException nfe) {
            return 0;
        }
        return profileService.MembershipWithDraw(Integer.parseInt(classNum));
    }

    @PostMapping("/cannonball/all")
    public List<Profile> showAllMember(){
        return profileService.allMember();
    }

}
