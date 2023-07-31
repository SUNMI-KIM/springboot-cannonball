package cannonball.cannonball.Service;

import cannonball.cannonball.Domain.RandomGroupApplication;
import cannonball.cannonball.Repository.RandomGroupApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomGroupApplicationService {
    RandomGroupApplyRepository randomGroupApplyRepository;

    @Autowired
    public RandomGroupApplicationService(RandomGroupApplyRepository randomGroupApplyRepository){
        this.randomGroupApplyRepository = randomGroupApplyRepository;
    }

    public int saveRandomApply(RandomGroupApplication randomGroupApplication){
        return randomGroupApplyRepository.saveApplication(randomGroupApplication);
    }

    public int withdrawRandomApply(String classNum, String randomName){
        return randomGroupApplyRepository.withdrawRandomApplication(classNum, randomName);
    }

    public int countOfApplicants(String randomName){
        return randomGroupApplyRepository.count(randomName);
    }
}
