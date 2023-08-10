package cannonball.cannonball.Service;

import cannonball.cannonball.Domain.RandomGroupApplication;
import cannonball.cannonball.Domain.Response;
import cannonball.cannonball.Repository.RandomGroupApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RandomGroupApplicationService {
    RandomGroupApplicationRepository randomGroupApplicationRepository;

    @Autowired
    public RandomGroupApplicationService(RandomGroupApplicationRepository randomGroupApplicationRepository){
        this.randomGroupApplicationRepository = randomGroupApplicationRepository;
    }

    public boolean saveRandomApply(RandomGroupApplication randomGroupApplication){
        if (randomGroupApplicationRepository.findbyName(
                randomGroupApplication.getRandomName(),
                randomGroupApplication.getClassNum())
                .isPresent()) {
            return false;
        }
        randomGroupApplicationRepository.saveApplication(randomGroupApplication);
        return true;
    }

    public boolean withdrawRandomApply(String classNum, String randomName){
        if (randomGroupApplicationRepository.withdrawRandomApplication(classNum, randomName) == 1) {
            return true;
        }
        return false;
    }

    public int countOfApplicants(String randomName){
        return randomGroupApplicationRepository.count(randomName);
    }
}
