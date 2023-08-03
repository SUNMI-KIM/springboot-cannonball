package cannonball.cannonball.Repository;

import cannonball.cannonball.Domain.RandomGroup;

import java.util.List;


public interface RandomGroupRepository {
    public void save(RandomGroup randomGroup);
    public int delete(String randomName);
    public List<RandomGroup> allRandomGroup();
}
