package cannonball.cannonball.Service;

import cannonball.cannonball.Domain.Profile;
import cannonball.cannonball.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            if (profile.get().getPassWord().equals(passWord)){
                return 1;
            }
        }
        return 0;
    }

    public int MembershipJoin(Profile profile){
        if (profileRepository.findById(profile.getClassNum()).isPresent()){
            return 0;
        }
        profileRepository.save(profile);
        return 1;
    }

    public void MembershipWithDraw(int classNum){
        profileRepository.deleteUser(classNum);
    }

    public List<Profile> allMember() { return profileRepository.findAll(); }

    private void validateDuplicateProfile(Profile profile) {
        profileRepository.findById(profile.getClassNum())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
