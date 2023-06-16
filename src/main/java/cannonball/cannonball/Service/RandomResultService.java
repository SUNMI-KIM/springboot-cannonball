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
        List<RandomResult> randomResults = resultRepository.findByName(randomName);
        int inGroupOf = resultRepository.findNumByName(randomName);
        int AllGroupNum = randomResults.size();


    }
}
