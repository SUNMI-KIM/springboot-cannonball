package cannonball.cannonball.Service;

import cannonball.cannonball.Domain.RandomGroup;
import cannonball.cannonball.Repository.RandomGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomGroupService {
    private final RandomGroupRepository randomGroupRepository;

    @Autowired
    public RandomGroupService(RandomGroupRepository randomGroupRepository){
        this.randomGroupRepository = randomGroupRepository;
    }
    public List<RandomGroup> showAllRandom(){
        return randomGroupRepository.allRandomGroup();
    }

    public RandomGroup makeRandom(RandomGroup randomGroup) {
        return randomGroupRepository.save(randomGroup);
    }

    public int deleteRandom(String randomName){
        return randomGroupRepository.delete(randomName);
    }

}
