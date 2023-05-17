package cannonball.cannonball.Service;

import cannonball.cannonball.Domain.Profile;
import cannonball.cannonball.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public int MembershipLogin(int classNum, String passWord){
        Optional<Profile> profile = profileRepository.findById(classNum);
        if (profile.isPresent()){
            if (profile.get().getPassWord() == passWord){
                return 1;
            }
        }
        return 0;
    }

    private void validateDuplicateProfile(Profile profile) {
        profileRepository.findById(profile.getClassNum())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
