package cannonball.cannonball.Repository;

import cannonball.cannonball.Domain.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    public Profile save(Profile profile);
    public Optional<Profile> findById(int classNum);
    public int deleteUser (int classNum);
    public List<Profile> findAll();
}
