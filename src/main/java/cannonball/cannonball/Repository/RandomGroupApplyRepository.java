package cannonball.cannonball.Repository;

import cannonball.cannonball.Domain.RandomGroupApplication;

import java.util.List;

public interface RandomGroupApplyRepository {
    public int saveApplication(RandomGroupApplication randomGroupApplication);
    public int withdrawRandomApplication(String classNum, String RandomName);
    public int count(String randomName);
}
