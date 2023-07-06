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
        jdbcTemplate.update("insert into randomresult value(?,?,?,?,?)",
                randomResult.getClassNum(),
                randomResult.getName(),
                randomResult.getGender(),
                randomResult.getRandomName(),
                randomResult.getGroupNum());
        return 1;
    }

    @Override
    public List<RandomResult> allRandomResult(String randomName) {
        return jdbcTemplate.query("select * from randomresult where randomName = ? order by groupNum", RandomResultRowMapper(), randomName);
    }

    @Override
    public int modify(int classNum, String randomName, int groupNum) {

        return 0;
    }

    public List<RandomResult> findByNameGender(String randomName, String gender) {
        return jdbcTemplate.query("select * from randomgroupapplication where randomName = ? and gender = ?", RandomGroupApplicationRowMapper(), randomName, gender);
    }

    public int findNumByName(String randomName){
        return jdbcTemplate.queryForObject("select inGroupOf from randomgroup where randomName = ?", Integer.class, randomName);
    }

    private RowMapper<RandomResult> RandomResultRowMapper(){
        return (rs, rowNum) -> {
            RandomResult randomResult = new RandomResult();
            randomResult.setGroupNum(rs.getInt("groupNum"));
            randomResult.setClassNum(rs.getInt("classNum"));
            randomResult.setName(rs.getString("name"));
            randomResult.setGender(rs.getString("gender"));
            randomResult.setRandomName(rs.getString("randomName"));
            return randomResult;
        };
    }

    private RowMapper<RandomResult> RandomGroupApplicationRowMapper(){
        return (rs, rowNum) -> {
            RandomResult randomResult = new RandomResult();
            randomResult.setGroupNum(0);
            randomResult.setClassNum(rs.getInt("classNum"));
            randomResult.setName(rs.getString("name"));
            randomResult.setGender(rs.getString("gender"));
            randomResult.setRandomName(rs.getString("randomName"));
            return randomResult;
        };
    }
}
