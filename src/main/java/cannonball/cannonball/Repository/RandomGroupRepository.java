package cannonball.cannonball.Repository;

import cannonball.cannonball.Domain.RandomGroup;

import java.util.List;


public interface RandomGroupRepository {
    public int save(RandomGroup randomGroup);
    public int delete(String randomName);
    public List<RandomGroup> allRandomGroup();
}
