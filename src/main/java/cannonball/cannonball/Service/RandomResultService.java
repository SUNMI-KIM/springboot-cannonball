package cannonball.cannonball.Service;

import cannonball.cannonball.Domain.RandomResult;
import cannonball.cannonball.Repository.ResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RandomResultService {

    private final ResultRepository resultRepository;

    @Autowired
    public RandomResultService(ResultRepository resultRepository){
        this.resultRepository = resultRepository;
    }

    public boolean makeRandomGroup(String randomName){
        List<RandomResult> randomResults = resultRepository.findByName(randomName);

        List<RandomResult> randomResultsGirl = new ArrayList<>();
        List<RandomResult> randomResultsBoy = new ArrayList<>();

        for (RandomResult randomResult : randomResults) {
            if (randomResult.getGender().equals("남")) randomResultsBoy.add(randomResult);
            else randomResultsGirl.add(randomResult);
        }

        int GroupNum = (randomResultsBoy.size() + randomResultsGirl.size()) / resultRepository.findNumByName(randomName);
        int manInGroupOf = randomResultsBoy.size() / GroupNum; int girlInGroupOf = randomResultsGirl.size() / GroupNum;
        int remainMan = randomResultsBoy.size() % manInGroupOf; int remainGirl = randomResultsGirl.size() / girlInGroupOf;
        Collections.shuffle(randomResultsBoy); Collections.shuffle(randomResultsGirl);

        try {
            // man
            for (int i = 0; i < GroupNum; i++) {
                for (int r = 0; r < manInGroupOf; r++) {
                    randomResultsBoy.get(i * GroupNum + r).setGroupNum(i);
                    resultRepository.save(randomResultsBoy.get(i * GroupNum + r));
                }
            }
            for (int i = 0; i < remainMan; i++) {
                randomResultsBoy.get(GroupNum * manInGroupOf + i).setGroupNum(i);
                resultRepository.save(randomResultsBoy.get(GroupNum * manInGroupOf + i));
            }

            // Girl
            for (int i = 0; i < GroupNum; i++) {
                for (int r = 0; r < girlInGroupOf; r++) {
                    randomResultsGirl.get(i * GroupNum + r).setGroupNum(i);
                    resultRepository.save(randomResultsGirl.get(i * GroupNum + r));
                }
            }
            for (int i = 0; i < remainGirl; i++) {
                randomResultsGirl.get(GroupNum * girlInGroupOf + i).setGroupNum(i);
                resultRepository.save(randomResultsGirl.get(GroupNum * girlInGroupOf + i));
            }
        }catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public boolean modifyGroup(RandomResult randomResults) {
        if (resultRepository.modify(randomResults) == 1) {
            return true;
        }
        return false;
    }

    public List<RandomResult> showAll(String randomName){
        return resultRepository.allRandomResult(randomName);
    }

}
