package cannonball.cannonball.Repository;

import cannonball.cannonball.Domain.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    public Profile save(Profile profile);
    public Optional<Profile> findById(String classNum);
    public int deleteUser (String classNum);
    public List<Profile> findAll();
    public int modify(String gender, String classNum);
}
