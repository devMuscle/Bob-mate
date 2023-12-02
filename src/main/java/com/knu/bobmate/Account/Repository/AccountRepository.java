package com.knu.bobmate.Account.Repository;

import com.knu.bobmate.Account.Dto.ProfileResDto;
import com.knu.bobmate.Account.Exception.Exceptions.LoginException;
import com.knu.bobmate.Account.Filter.LoginCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class AccountRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountRepository(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    public String login(String id, String password) {
        try {
            int userId = this.jdbcTemplate.queryForObject("SELECT User_id FROM user_info WHERE Login_id = ? AND Password = ?", Integer.class, id, password);
            String token = UUID.randomUUID().toString();
            LoginCheckFilter.token.put(token, userId);
            return token;
        } catch (Exception e) {
            throw new LoginException("로그인에 실패하였습니다.");
        }
    }

    public void register(String id, String password, String nickname, String email) {
        try {
            int registerEffected = this.jdbcTemplate.update("INSERT INTO USER_INFO VALUES (user_id_seq.NEXTVAL, ?, ?, ?, ?, 0)", id, password, nickname, email);
            if (registerEffected == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new LoginException("회원가입이 실패하였습니다.");
        }
    }


    public ProfileResDto profile(int userId) {
        try {
            ProfileResDto profileResDto = this.jdbcTemplate.queryForObject(
            "SELECT NICK_NAME, MESSENGER_LINK FROM USER_INFO WHERE USER_ID = ?",
                new Object[]{userId},
                new RowMapper<ProfileResDto>() {
                    @Override
                    public ProfileResDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ProfileResDto profileResDto = new ProfileResDto();
                        profileResDto.setNickname(rs.getString(1));
                        profileResDto.setEmail(rs.getString(2));
                        return profileResDto;
                    }
                }
            );

            return profileResDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoginException("프로필을 가져오는데 문제가 생겼습니다.");
        }
    }
}
