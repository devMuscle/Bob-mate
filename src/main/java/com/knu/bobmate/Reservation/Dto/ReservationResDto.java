package com.knu.bobmate.Reservation.Dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResDto {
    private int reservationId;
    private Timestamp dateTime;
    private String description;
    private int penaltyPrice;
    private boolean finished;
    private int restaurantId;
}
