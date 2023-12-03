package com.knu.bobmate.Reservation.Repository;

import com.knu.bobmate.Reservation.Dto.CreateReservationDto;
import com.knu.bobmate.Reservation.Dto.JoinReservationReqDto;
import com.knu.bobmate.Reservation.Dto.ReservationResDto;
import com.knu.bobmate.Reservation.Exception.Exceptions.ReservationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ReservationRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReservationRepository(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }


    public ArrayList<ReservationResDto> myReservationList(int userId) {
        try {
            ArrayList<ReservationResDto> myReservationList = this.jdbcTemplate.queryForObject(
                    "SELECT DISTINCT R.Reservation_id, R.Date_time, R.Description, R.Finished, R.Restaurant_id, R.PENALTY_PRICE FROM Reservation R, Participant P WHERE R.RESERVATION_ID = P.RESERVATION_ID AND P.USER_ID = ?",
                    new Object[]{userId},
                    new RowMapper<ArrayList<ReservationResDto>>() {
                        @Override
                    public ArrayList<ReservationResDto> mapRow(ResultSet rs, int rowNum) throws SQLException {
                            ArrayList<ReservationResDto> reservations = new ArrayList<>();
                            while(rs.next()) {
                                reservations.add(
                                        ReservationResDto
                                                .builder()
                                                .reservationId(rs.getInt(1))
                                                .dateTime(rs.getTimestamp(2))
                                                .description(rs.getString(3))
                                                .finished(rs.getBoolean(4))
                                                .restaurantId(rs.getInt(5))
                                                .penaltyPrice(rs.getInt(6))
                                                .build()
                                );
                            }
                            return reservations;
                    }
                }
            );
            return myReservationList;
        } catch (Exception e) {
            throw new ReservationException("자신의 모든 약속을 조회하는데 실패하였습니다.");
        }
    }

    public List<ReservationResDto> findAllReservation() {
        String sql = "SELECT r.Reservation_id, r.Date_time, r.Description, r.Penalty_price, r.Finished, " +
                "p.ParticipantCount, rs.Name, rs.Location  " +
                "FROM RESERVATION r " +
                "LEFT JOIN (SELECT Reservation_id, COUNT(*) AS ParticipantCount FROM PARTICIPANT GROUP BY Reservation_id) p ON r.Reservation_id = p.Reservation_id " +
                "LEFT JOIN RESTAURANT rs ON r.Restaurant_id = rs.Restaurant_id";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                ReservationResDto reservationResDto = new ReservationResDto();
                reservationResDto.setReservationId(rs.getInt(1));
                reservationResDto.setDateTime(rs.getTimestamp(2));
                reservationResDto.setDescription(rs.getString(3));
                reservationResDto.setPenaltyPrice(rs.getInt(4));
                reservationResDto.setFinished(rs.getInt(5) == 1);
                reservationResDto.setParticipantCnt(rs.getInt(6));
                reservationResDto.setRestaurantName(rs.getString(7));
                reservationResDto.setRestaurantLocation(rs.getString(8));
                return reservationResDto;
            });
        }catch (Exception e) {
            throw new ReservationException("예약 전체 조회에 실패했습니다.");
        }
    }

    @Transactional
    public void finishReservation(int reservationId) {
        String updateReservationSql = "UPDATE RESERVATION SET Finished = 1 WHERE Reservation_id = ?";
        jdbcTemplate.update(updateReservationSql, reservationId);

        String updatePointsSql = "UPDATE USER_INFO u " +
                "SET u.Point = u.Point - (SELECT DISTINCT r.Penalty_price " +
                "                         FROM RESERVATION r " +
                "                         JOIN PARTICIPANT p ON p.Reservation_id = r.Reservation_id " +
                "                         WHERE r.Reservation_id = ? AND p.Participation = 0) " +
                "WHERE EXISTS (" +
                "    SELECT 1 " +
                "    FROM PARTICIPANT p " +
                "    WHERE u.User_id = p.User_id AND p.Reservation_id = ? AND p.Participation = 0)";

        jdbcTemplate.update(updatePointsSql, reservationId, reservationId);
    }

    public void createReservation(CreateReservationDto createReservationDto, int userId) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            Date date = new Date(createReservationDto.getDateTime().getTime());
            this.jdbcTemplate.update(
                    (Connection con) -> {
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO RESERVATION VALUES (RESERVATION_SEQ.nextval, ?, ?, ?, 0, ?)", Statement.RETURN_GENERATED_KEYS);
                    pstmt.setDate(1, date);
                    pstmt.setString(2, createReservationDto.getDescription());
                    pstmt.setInt(3, createReservationDto.getPenaltyPrice());
                    pstmt.setInt(4, createReservationDto.getRestaurantId());
                    return pstmt;
                }
            );

            this.jdbcTemplate.update(
                    (Connection con) -> {
                        PreparedStatement pstmt = con.prepareStatement("INSERT INTO PARTICIPANT (PARTICIPANT_ID, ROLE, PARTICIPATION, USER_ID, RESERVATION_ID) VALUES(PARTICIPANT_SEQ.nextval, 'HOST', 0, ?, RESERVATION_SEQ.currval)");
                        pstmt.setInt(1, userId);
                        return pstmt;
                    }
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw new ReservationException("예약 생성에 문제가 생겼습니다.");
        }
    }

    public void joinReservation(int userId, JoinReservationReqDto joinReservationReqDto) {
        try {
            int a = this.jdbcTemplate.update(
                    (Connection con) -> {
                        PreparedStatement pstmt = con.prepareStatement("INSERT INTO PARTICIPANT (PARTICIPANT_ID, ROLE, PARTICIPATION, USER_ID, RESERVATION_ID) VALUES(PARTICIPANT_SEQ.nextval, 'GUEST', 0, ?, ?)");
                        pstmt.setInt(1, userId);
                        pstmt.setInt(2, joinReservationReqDto.getReservationId());
                        return pstmt;
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new ReservationException("예약 참여에 문제가 생겼습니다.");
        }
    }
}
