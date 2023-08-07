package cannonball.cannonball.Service;

import cannonball.cannonball.Domain.RandomGroup;
import cannonball.cannonball.Repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public boolean makeRandom(RandomGroup randomGroup) {
        if (groupRepository.findByName(randomGroup.getRandomName()).isPresent()) {
            return false;
        }
        groupRepository.save(randomGroup);
        return true;
    }

    public boolean deleteRandom(String randomName){
        if (groupRepository.delete(randomName) == 1) {
            return true;
        }
        return false;
    }

    public boolean modifyRandom(String randomName, int inGroupOf) {
        if (groupRepository.modify(randomName, inGroupOf) == 1) {
            return true;
        }
        return false;
    }

    public Date findRandomDate(String randomName) {
        Optional<RandomGroup> randomGroup = groupRepository.findByName(randomName);
        return randomGroup.get().getRaiseRandom();
    }

}
