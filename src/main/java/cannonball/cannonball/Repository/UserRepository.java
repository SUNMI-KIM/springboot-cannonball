package cannonball.cannonball.Repository;

import cannonball.cannonball.Domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements ProfileRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Profile save(Profile profile)
    {
        String sql = "insert into profile values(?,?,?,?,?,?)";
        int result = jdbcTemplate.update(sql,
                profile.getClassNum(),
                profile.getName(),
                profile.getGender(),
                profile.getPassWord(),
                profile.getPhoneNum(),
                profile.getClassName());
        return profile;
    }

    @Override
    public Optional<Profile> findById(String classNum) {
        List<Profile> result = jdbcTemplate.query("select * from profile where classNum = ?", profileRowMapper(), classNum);
        return result.stream().findAny();
    }

    @Override
    public int deleteUser(String classNum) {
        String sql = "delete from profile where classNum = ?";
        int result = jdbcTemplate.update(sql, classNum);
        return result;
    }

    @Override
    public List<Profile> findAll() {
        return jdbcTemplate.query("select * from profile", profileRowMapper());
    }

    @Override
    public int modify(String classNum, String gender) {
        String sql = "update profile set gender =? where classNum =?";
        int result = jdbcTemplate.update(sql, gender, classNum);
        return result;
    }

    private RowMapper<Profile> profileRowMapper(){
        return (rs, rowNum) -> {
            Profile profile = new Profile();
            profile.setClassNum(rs.getString("classNum"));
            profile.setPassWord(rs.getString("passWord"));
            profile.setPhoneNum(rs.getString("phoneNum"));
            profile.setName(rs.getString("name"));
            profile.setGender(rs.getString("gender"));
            profile.setClassName(rs.getString("className"));
            return profile;
        };
    }
}
