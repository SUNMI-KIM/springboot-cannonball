package cannonball.cannonball.Repository;

import cannonball.cannonball.Domain.RandomGroupApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

    public int saveApplication(RandomGroupApplication randomGroupApplication) {
        String sql = "insert into randomgroupapplication values(?,?,?,?)";
        int result = jdbcTemplate.update(sql,
                randomGroupApplication.getClassNum(),
                randomGroupApplication.getName(),
                randomGroupApplication.getGender(),
                randomGroupApplication.getRandomName());
        return result;
    }

    public int withdrawRandomApplication(String classNum, String RandomName) {
        String sql = "delete from randomgroupapplication where randomName=? and classNum=?";
        int result = jdbcTemplate.update(sql, RandomName, classNum);
        return result;
    }

    public int count(String randomName) {
        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from randomgroupapplication where randomName=?", Integer.class, randomName);
        return count;
    }

    public Optional<RandomGroupApplication> findbyName(String RandomName, String classNum) {
        String sql = "select * from randomgroupapplication where randomName=? and classNum=?";
        List<RandomGroupApplication> randomGroupApplications = jdbcTemplate.query(sql,RandomGroupApplicationRowMapper(), RandomName, classNum);
        return randomGroupApplications.stream().findAny();
    }

    private RowMapper<RandomGroupApplication> RandomGroupApplicationRowMapper(){
        return (rs, rowNum) -> {
            RandomGroupApplication randomGroupApplication = new RandomGroupApplication();
            randomGroupApplication.setRandomName(rs.getString("randomname"));
            randomGroupApplication.setGender(rs.getString("gender"));
            randomGroupApplication.setName(rs.getString("name"));
            randomGroupApplication.setClassNum(rs.getString("classnum"));
            return randomGroupApplication;
        };
    }
}
