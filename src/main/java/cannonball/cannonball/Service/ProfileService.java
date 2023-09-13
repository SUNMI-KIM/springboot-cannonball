package cannonball.cannonball.Service;

import cannonball.cannonball.Domain.Profile;
import cannonball.cannonball.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder){
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean MembershipLogin(String classNum, String passWord){
        Optional<Profile> profile = profileRepository.findById(classNum);
        if (profile.isPresent()){
            if (profile.get().checkPassWord(passWord, passwordEncoder))
                return true;
        }
        return false;
    }

    public boolean MembershipJoin(Profile profile){
        if (profileRepository.findById(profile.getClassNum()).isPresent()){
            return false;
        }
        profile.hashPassWord(passwordEncoder);
        profileRepository.save(profile);
        return true;
    }

    public boolean MembershipWithDraw(String classNum){
        if (profileRepository.deleteUser(classNum) == 1) {
            return true;
        }
        return false;
    }

    public List<Profile> allMember() {
        return profileRepository.findAll();
    }

    public boolean modifyMember(Profile profile) {
        if (profileRepository.modify(profile) == 1) {
            return true;
        }
        return false;
    }

    private void validateDuplicateProfile(Profile profile) {
        profileRepository.findById(profile.getClassNum())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
