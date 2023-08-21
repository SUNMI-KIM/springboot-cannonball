package cannonball.cannonball.Service;

import cannonball.cannonball.DTO.RandomApplicationDto;
import cannonball.cannonball.Domain.RandomGroupApplication;
import cannonball.cannonball.Repository.RandomGroupApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RandomGroupApplicationService {
    RandomGroupApplicationRepository randomGroupApplicationRepository;

    public RandomGroupApplicationService(RandomGroupApplicationRepository randomGroupApplicationRepository) {
        this.randomGroupApplicationRepository = randomGroupApplicationRepository;
    }

    public boolean application(RandomGroupApplication randomGroupApplication) {
        if (randomGroupApplicationRepository.findByName(
                randomGroupApplication.getRandomName(), randomGroupApplication.getClassNum()).isPresent()) {
            return false;
        }
        randomGroupApplicationRepository.save(randomGroupApplication);
        return true;
    }

    public boolean withdraw(String randomName, String classNum) {
        if (randomGroupApplicationRepository.findByName(
                randomName, classNum).isPresent()) {
            return false;
        }
        randomGroupApplicationRepository.delete(randomName, classNum);
        return true;
    }

    public int countOfApplicants(String randomName) {
        return randomGroupApplicationRepository.countForApplicants(randomName);
    }

    public List<RandomApplicationDto> organizationShow(String randomName) {
        return randomGroupApplicationRepository.findAll(randomName);
    }

    public boolean groupOrganization(String randomName) {
        List<RandomApplicationDto> randomResults;
        randomResults = randomGroupApplicationRepository.findAll(randomName);

        List<RandomApplicationDto> randomResultsGirl = new ArrayList<>();
        List<RandomApplicationDto> randomResultsBoy = new ArrayList<>();

        for (RandomApplicationDto randomResult : randomResults) {
            if (randomResult.getGender().equals("남")) randomResultsBoy.add(randomResult);
            else randomResultsGirl.add(randomResult);
        }

        int GroupNum = (randomResultsBoy.size() + randomResultsGirl.size())
                / randomGroupApplicationRepository.countForApplicants(randomName);
        int manInGroupOf = randomResultsBoy.size() / GroupNum;
        int girlInGroupOf = randomResultsGirl.size() / GroupNum;
        int remainMan = randomResultsBoy.size() % manInGroupOf;
        int remainGirl = randomResultsGirl.size() / girlInGroupOf;
        Collections.shuffle(randomResultsBoy); Collections.shuffle(randomResultsGirl);

        try {
            // man
            for (int i = 0; i < GroupNum; i++) {
                for (int r = 0; r < manInGroupOf; r++) {
                    randomResultsBoy.get(i * GroupNum + r).setGroupNum(i);
                    randomGroupApplicationRepository.modify(randomResultsBoy.get(i * GroupNum + r));
                }
            }
            for (int i = 0; i < remainMan; i++) {
                randomResultsBoy.get(GroupNum * manInGroupOf + i).setGroupNum(i);
                randomGroupApplicationRepository.modify(randomResultsBoy.get(GroupNum * manInGroupOf + i));
            }

            // Girl
            for (int i = 0; i < GroupNum; i++) {
                for (int r = 0; r < girlInGroupOf; r++) {
                    randomResultsGirl.get(i * GroupNum + r).setGroupNum(i);
                    randomGroupApplicationRepository.modify(randomResultsGirl.get(i * GroupNum + r));
                }
            }
            for (int i = 0; i < remainGirl; i++) {
                randomResultsGirl.get(GroupNum * girlInGroupOf + i).setGroupNum(i);
                randomGroupApplicationRepository.modify(randomResultsGirl.get(GroupNum * girlInGroupOf + i));
            }
        }catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;

    }

}
