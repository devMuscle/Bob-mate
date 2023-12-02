package com.knu.bobmate.Reservation.Repository;

import com.knu.bobmate.Account.Dto.ProfileResDto;
import com.knu.bobmate.Reservation.Dto.ReservationResDto;
import com.knu.bobmate.Reservation.Exception.Exceptions.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
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
}
