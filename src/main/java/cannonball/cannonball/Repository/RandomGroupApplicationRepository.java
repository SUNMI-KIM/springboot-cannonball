package cannonball.cannonball.Repository;

import cannonball.cannonball.DTO.RandomApplicationDto;
import cannonball.cannonball.Domain.RandomGroupApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class RandomGroupApplicationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RandomGroupApplicationRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int save(RandomGroupApplication randomGroupApplication) {
        int result = jdbcTemplate.update("insert into randomgroupapplication values(?,?,?)",
                randomGroupApplication.getClassNum(), randomGroupApplication.getRandomName(), 0);
        return result;
    }

    public int delete(String classNum, String randomName) {
        int result = jdbcTemplate.update("delete from randomgroupapplication where classNum=? and randomName=?", classNum, randomName);
        return result;
    }

    public int modify(RandomApplicationDto randomApplicationDto) {
        int result = jdbcTemplate.update("update randomgroupapplication set groupNum=? where randomName=? and classNum=?",
                randomApplicationDto.getGroupNum(), randomApplicationDto.getRandomName(), randomApplicationDto.getClassNum());
        return result;
    }

    public int countForApplicants(String randomName) {
        int result = jdbcTemplate.queryForObject("select count(*) from randomgroupapplication where randomName=?"
                , Integer.class, randomName);
        return result;
    }

    public int getInGroupOf(String randomName) {
        int result = jdbcTemplate.queryForObject("select inGroupOf from randomgroup where randomName=?",
                Integer.class, randomName);
        return result;
    }

    public List<RandomApplicationDto> findAll(String randomName) {
        String sql = "select a.classNum, a.randomName, a.groupNum, b.name, b.gender " +
                "from randomgroupapplication a join profile b on a.classNum = b.classNum where randomName=? order by groupNum";
        return jdbcTemplate.query(sql, randomApplicationDtoRowMapper(), randomName);
    }

    public Optional<RandomGroupApplication> findByName(String randomName, String classNum) {
        List<RandomGroupApplication> randomGroupApplications = jdbcTemplate.query(
                "select * from randomgroupapplication where randomName=? and classNum=?",
                randomGroupApplicationRowMapper(), randomName, classNum);
        return randomGroupApplications.stream().findAny();
    }

    public RowMapper<RandomGroupApplication> randomGroupApplicationRowMapper() {
        return (rs, rowNum) -> {
            RandomGroupApplication randomGroupApplication = new RandomGroupApplication();
            randomGroupApplication.setClassNum(rs.getString("classNum"));
            randomGroupApplication.setRandomName(rs.getString("randomName"));
            randomGroupApplication.setGroupNum(rs.getInt("groupNum"));
            return randomGroupApplication;
        };
    }

    public RowMapper<RandomApplicationDto> randomApplicationDtoRowMapper() {
        return (rs, rowNum) -> {
            RandomApplicationDto randomApplicationDto = new RandomApplicationDto();
            randomApplicationDto.setRandomName(rs.getString("randomName"));
            randomApplicationDto.setGender(rs.getString("gender"));
            randomApplicationDto.setName(rs.getString("name"));
            randomApplicationDto.setClassNum(rs.getString("classNum"));
            randomApplicationDto.setGroupNum(rs.getInt("groupNum"));
            return randomApplicationDto;
        };
    }


}
