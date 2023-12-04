package com.knu.bobmate.Reservation.Controller;

import com.knu.bobmate.Reservation.Dto.CreateReservationDto;
import com.knu.bobmate.Reservation.Dto.JoinReservationReqDto;
import com.knu.bobmate.Reservation.Dto.ReservationDto;
import com.knu.bobmate.Reservation.Dto.ReservationResDto;
import com.knu.bobmate.Reservation.Service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reservation")
public class ReservationController {
    ReservationService reservationService;

    ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/my")
    public ResponseEntity<ArrayList<ReservationResDto>> myReservationList(@RequestAttribute(name="userId") int userId) {
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

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> viewReservation(@PathVariable("reservationId") int reservationId) {
        ReservationDto reservationDto = reservationService.viewReservation(reservationId);

        return ResponseEntity.ok(reservationDto);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createReservation(@RequestAttribute(name="userId") int userId, @RequestBody @Valid CreateReservationDto createReservationDto) {
        reservationService.createReservation(createReservationDto, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<HttpStatus> joinReservation(@RequestAttribute(name="userId") int userId, @RequestBody @Valid JoinReservationReqDto joinReservationReqDto) {
        reservationService.joinReservation(userId, joinReservationReqDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
