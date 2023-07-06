package cannonball.cannonball.Service;

import cannonball.cannonball.Domain.RandomResult;
import cannonball.cannonball.Repository.RandomResultRepository;
import cannonball.cannonball.Repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomResultService {

    private final ResultRepository resultRepository;

    @Autowired
    public RandomResultService(ResultRepository resultRepository){
        this.resultRepository = resultRepository;
    }

    public int makeRandomGroup(String randomName){
        List<RandomResult> randomResultsGirl = resultRepository.findByNameGender(randomName, "여");
        List<RandomResult> randomResultsBoy = resultRepository.findByNameGender(randomName, "남");
        int GroupNum = (randomResultsBoy.size() + randomResultsGirl.size()) / resultRepository.findNumByName(randomName);
        if (randomResultsBoy.size() > randomResultsGirl.size()) {
            for (int i = 0; i < GroupNum; i++){
                for (int r = i * (randomResultsGirl.size() / GroupNum); r < i + (randomResultsGirl.size() / GroupNum); r++) {
                    randomResultsGirl.get(r).setGroupNum(i + 1);
                    resultRepository.save(randomResultsGirl.get(r));
                }
                for (int r = i * (randomResultsBoy.size() / GroupNum); r < i + (randomResultsBoy.size() / GroupNum); r++) {
                    randomResultsBoy.get(r).setGroupNum(i + 1);
                    resultRepository.save(randomResultsBoy.get(r));
                }
            }
            int Group = 1;
            for (int i = GroupNum * (randomResultsGirl.size() / GroupNum); i < randomResultsGirl.size(); i++) {
                randomResultsGirl.get(i).setGroupNum(Group++);
                resultRepository.save(randomResultsGirl.get(i));
            }
            Group = 1;
            for (int i = GroupNum * (randomResultsBoy.size() / GroupNum); i < randomResultsBoy.size(); i++) {
                randomResultsBoy.get(i).setGroupNum(Group++);
                resultRepository.save(randomResultsBoy.get(i));
            }
        }
        return 1;
    }

    public List<RandomResult> showAll(String randomName){
        return resultRepository.allRandomResult(randomName);
    }

}
