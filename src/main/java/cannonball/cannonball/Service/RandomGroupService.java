package cannonball.cannonball.Service;

import cannonball.cannonball.Domain.RandomGroup;
import cannonball.cannonball.Repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomGroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public RandomGroupService(GroupRepository randomGroupRepository){
        this.groupRepository = randomGroupRepository;
    }
    public List<RandomGroup> showAllRandom(){
        return groupRepository.allRandomGroup();
    }

    public int makeRandom(RandomGroup randomGroup) {
        if (groupRepository.findByName(randomGroup.getRandomName()).isPresent()) {
            return 0;
        }
        return 1;
    }

    public int deleteRandom(String randomName){
        return groupRepository.delete(randomName);
    }

}
