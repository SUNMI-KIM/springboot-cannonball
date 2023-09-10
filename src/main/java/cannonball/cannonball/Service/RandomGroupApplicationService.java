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

    public boolean withdraw(String classNum, String randomName) {
        if (randomGroupApplicationRepository.findByName(
                randomName, classNum).isPresent()) {
            randomGroupApplicationRepository.delete(classNum, randomName);
            return true;
        }
        return false;
    }

    public int countOfApplicants(String randomName) {
        return randomGroupApplicationRepository.countForApplicants(randomName);
    }

    public List<RandomApplicationDto> organizationShow(String randomName) {
        return randomGroupApplicationRepository.findAll(randomName);
    }

    public boolean groupOrganization(String randomName) {

        try {

        List<RandomApplicationDto> randomResults;
        randomResults = randomGroupApplicationRepository.findAll(randomName);

        List<RandomApplicationDto> randomResultsGirl = new ArrayList<>();
        List<RandomApplicationDto> randomResultsBoy = new ArrayList<>();

        for (RandomApplicationDto randomResult : randomResults) {
            if (randomResult.getGender().equals("남")) randomResultsBoy.add(randomResult);
            else randomResultsGirl.add(randomResult);
        }

        int GroupNum = (randomResultsBoy.size() + randomResultsGirl.size())
                / randomGroupApplicationRepository.getInGroupOf(randomName);
        Collections.shuffle(randomResultsBoy); Collections.shuffle(randomResultsGirl);

        // man
        for (int i = 0; i < randomResultsBoy.size(); i++) {
            randomResultsBoy.get(i).setGroupNum(i % GroupNum + 1);
            randomGroupApplicationRepository.modify(randomResultsBoy.get(i));
        }

        // girl
        for (int i = 0; i < randomResultsGirl.size(); i++) {
            randomResultsGirl.get(i).setGroupNum(i % GroupNum + 1);
            randomGroupApplicationRepository.modify(randomResultsGirl.get(i));
        }

        }catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean modifyRandomGroup(RandomGroupApplication randomGroupApplication) {
        if (randomGroupApplicationRepository.findByName(randomGroupApplication.getRandomName(), randomGroupApplication.getClassNum()).isPresent()) {
            RandomApplicationDto randomApplicationDto = new RandomApplicationDto();
            randomApplicationDto.setRandomName(randomGroupApplication.getRandomName());
            randomApplicationDto.setClassNum(randomGroupApplication.getClassNum());
            randomApplicationDto.setGroupNum(randomGroupApplication.getGroupNum());
            randomGroupApplicationRepository.modify(randomApplicationDto);
            return true;
        }
        return false;
    }

}
