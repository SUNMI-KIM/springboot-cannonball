package cannonball.cannonball.Repository;

import cannonball.cannonball.Domain.RandomResult;

import java.util.List;

public interface RandomResultRepository {
    public int save(RandomResult randomResult);
    public List<RandomResult> allRandomResult(String randomName);
    public int modify(int classNum, String randomName, int groupNum);
}
