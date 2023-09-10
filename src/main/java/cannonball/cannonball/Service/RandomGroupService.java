package cannonball.cannonball.Service;

import cannonball.cannonball.Domain.RandomGroup;
import cannonball.cannonball.DTO.Response;
import cannonball.cannonball.Repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean modifyRandom(RandomGroup randomGroup) {
        if (groupRepository.modify(randomGroup) == 1) {
            return true;
        }
        return false;
    }

    public Response findRandomDate(String randomName) {
        Optional<RandomGroup> randomGroup = groupRepository.findByName(randomName);
        if (randomGroup.isPresent()) {
            return new Response("랜덤 조 올리는 날짜", randomGroup.get().getRaiseRandom());
        }
        return new Response("랜덤 조가 없음", 0);
    }

}
