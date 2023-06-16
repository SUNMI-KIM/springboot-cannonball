package cannonball.cannonball.Repository;

import cannonball.cannonball.Domain.RandomGroupApplication;
import cannonball.cannonball.Domain.RandomResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultRepository implements RandomResultRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ResultRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(RandomResult randomResult) {
        return 0;
    }

    @Override
    public List<RandomResult> allRandomResult(String randomName) {
        return null;
    }

    @Override
    public int modify(int classNum, String randomName, int groupNum) {
        return 0;
    }

    public List<RandomResult> findByName(String randomName) {
        return jdbcTemplate.query("select * from randomgroupapplication where randomName = ?", RandomResultRowMapper(), randomName);
    }

    public int findNumByName(String randomName){
        return jdbcTemplate.queryForObject("select inGroupOf from randomgroup where randomName = ?", Integer.class, randomName);
    }

    private RowMapper<RandomResult> RandomResultRowMapper(){
        return (rs, rowNum) -> {
            RandomResult randomResult = new RandomResult();
            randomResult.setClassNum(rs.getInt("classNum"));
            randomResult.setName(rs.getString("name"));
            randomResult.setGender(rs.getString("gender"));
            randomResult.setRandomName(rs.getString("randomName"));
            return randomResult;
        };
    }
}
