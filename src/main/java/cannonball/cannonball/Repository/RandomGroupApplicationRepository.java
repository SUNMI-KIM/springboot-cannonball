package cannonball.cannonball.Repository;

import cannonball.cannonball.Domain.RandomGroupApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RandomGroupApplicationRepository implements RandomGroupApplyRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RandomGroupApplicationRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int saveApplication(RandomGroupApplication randomGroupApplication) {
        String sql = "insert into randomgroupapplication values(?,?,?,?)";
        jdbcTemplate.update(sql,
                randomGroupApplication.getClassNum(),
                randomGroupApplication.getName(),
                randomGroupApplication.getGender(),
                randomGroupApplication.getRandomName());
        return 1;
    }

    @Override
    public int withdrawRandomApplication(String classNum, String RandomName) {
        String sql = "delete from randomgroupapplication where randomName = ? and classNum = ?";
        int result = jdbcTemplate.update(sql, RandomName, classNum);
        return 1;
    }

    @Override
    public int count(String randomName) {
        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from randomgroupapplication where randomName = ?", Integer.class, randomName);
        return count;
    }
}
