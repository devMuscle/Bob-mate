package com.knu.bobmate.Reservation.Controller;

import com.knu.bobmate.Reservation.Dto.ReservationResDto;
import com.knu.bobmate.Reservation.Service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    ReservationService reservationService;

    ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/my")
    public ResponseEntity<ArrayList<ReservationResDto>> myReservationList(@RequestAttribute int userId) {
        ArrayList<ReservationResDto> myReservationList = reservationService.myReservationList(userId);
        return ResponseEntity.ok(myReservationList);
    }
}
