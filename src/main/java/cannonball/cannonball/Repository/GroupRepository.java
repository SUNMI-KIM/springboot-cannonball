package cannonball.cannonball.Repository;

import cannonball.cannonball.Domain.RandomGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class GroupRepository implements RandomGroupRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(RandomGroup randomGroup) {
        String sql = "insert into randomgroup values(?,?,?,?,?,?)";
        int result = jdbcTemplate.update(sql,
                            randomGroup.getRandomName(),
                            randomGroup.getBoyGirlNum(),
                            randomGroup.getDeadLine(),
                            randomGroup.getRaiseRandom(),
                            randomGroup.getInGroupOf(),
                            randomGroup.getStartRandom());
        return result;
    }

    @Override
    public int delete(String randomName) {
        String sql = "delete from randomgroup where randomName = ?";
        int result = jdbcTemplate.update(sql, randomName);
        return result;
    }

    @Override
    public List<RandomGroup> allRandomGroup() {
        return jdbcTemplate.query("select * from randomgroup", RandomGroupRowMapper());
    }

    public Optional<RandomGroup> findByName(String randomName) {
        List<RandomGroup> randomGroups = jdbcTemplate.query("select * from randomgroup where randomName=?", RandomGroupRowMapper(), randomName);
        return randomGroups.stream().findAny();
    }

    public int modify(RandomGroup randomGroup) {
        String sql = "update randomgroup set inGroupOf=?, boyGirlNum=?, deadLine=?, startRandom=?, raiseRandom=? where randomName=?";
        int result = jdbcTemplate.update(sql,
                randomGroup.getInGroupOf(),
                randomGroup.getBoyGirlNum(),
                randomGroup.getDeadLine(),
                randomGroup.getStartRandom(),
                randomGroup.getRaiseRandom(),
                randomGroup.getRandomName());
        return result;
    }

    private RowMapper<RandomGroup> RandomGroupRowMapper(){
        return (rs, rowNum) -> {
            RandomGroup randomGroup = new RandomGroup();
            randomGroup.setRandomName(rs.getString("randomName"));
            randomGroup.setBoyGirlNum(rs.getInt("boyGirlNum"));
            randomGroup.setDeadLine(rs.getDate("deadLine"));
            randomGroup.setRaiseRandom(rs.getDate("raiseRandom"));
            randomGroup.setInGroupOf(rs.getInt("inGroupOf"));
            randomGroup.setStartRandom(rs.getDate("startRandom"));
            return randomGroup;
        };
    }
}
