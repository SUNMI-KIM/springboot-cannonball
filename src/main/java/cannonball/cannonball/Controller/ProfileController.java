package cannonball.cannonball.Controller;

import cannonball.cannonball.Domain.Profile;
import cannonball.cannonball.Domain.Response;
import cannonball.cannonball.Domain.ResponseList;
import cannonball.cannonball.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/cannonball/join")
    public ResponseEntity<Response> join (@RequestBody Profile profile) {
        if (profileService.MembershipJoin(profile)) {
            return ResponseEntity.accepted().body(new Response("회원 가입 성공", 1));
        }
        return ResponseEntity.badRequest().body(new Response("회원 가입 실패", 0));
    }

    @GetMapping("/cannonball/login")
    public ResponseEntity<Response> login (@RequestBody Map<String, String> profileMap) {
        String classNum = profileMap.get("classNum");
        String password = profileMap.get("passWord");
        if (profileService.MembershipLogin(classNum, password)) {
            return ResponseEntity.ok().body(new Response("로그인 성공", 0));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("로그인 실패", 1));
    }

    @DeleteMapping("/cannonball/profile")
    public ResponseEntity<Response> withdraw (@RequestBody Map<String, String> classNumMap) {
        String classNum = classNumMap.get("classNum");
        if (profileService.MembershipWithDraw(classNum)) {
            return ResponseEntity.ok().body(new Response("삭제 성공", 1));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("삭제 실패", 0));
    }

    @GetMapping("/cannonball/profile")
    public ResponseEntity<ResponseList> showAllMember(){
        List<Profile> profiles = profileService.allMember();
        return ResponseEntity.ok().body(new ResponseList("모든 회원 정보 조회", profiles, profiles.size()));
    }

    @PutMapping("cannonball/profile$")
    public ResponseEntity<Response> modifyProfile(@RequestBody Map<String, String> profileMap) {
        String classNum = profileMap.get("classNum");
        String gender = profileMap.get("gender");
        if (profileService.modifyMember(classNum, gender)) {
            return ResponseEntity.accepted().body(new Response("수정 성공", 1));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("수정 실패", 0));
    }

}
