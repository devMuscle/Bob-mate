package com.knu.bobmate.Reservation.Controller;

import com.knu.bobmate.Reservation.Dto.CreateReservationDto;
import com.knu.bobmate.Reservation.Dto.JoinReservationReqDto;
import com.knu.bobmate.Reservation.Dto.ReservationResDto;
import com.knu.bobmate.Reservation.Service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<ReservationResDto>> viewReservations() {
        return ResponseEntity.ok(reservationService.viewReservations());
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Void> finishReservation(@PathVariable("reservationId") int reservationId) {
        reservationService.finishReservation(reservationId);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createReservation(@RequestAttribute int userId, @RequestBody @Valid CreateReservationDto createReservationDto) {
        reservationService.createReservation(createReservationDto, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<HttpStatus> joinReservation(@RequestAttribute int userId, @RequestBody @Valid JoinReservationReqDto joinReservationReqDto) {
        reservationService.joinReservation(userId, joinReservationReqDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}