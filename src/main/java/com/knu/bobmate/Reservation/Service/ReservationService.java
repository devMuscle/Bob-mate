package com.knu.bobmate.Reservation.Service;

import com.knu.bobmate.Reservation.Dto.CreateReservationDto;
import com.knu.bobmate.Reservation.Dto.JoinReservationReqDto;
import com.knu.bobmate.Reservation.Dto.ReservationDto;
import com.knu.bobmate.Reservation.Dto.ReservationResDto;
import com.knu.bobmate.Reservation.Repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ArrayList<ReservationResDto> myReservationList(int userId) {
        ArrayList<ReservationResDto> myReservationList = reservationRepository.myReservationList(userId);
        return myReservationList;
    }

    public List<ReservationResDto> viewReservations() {
        return reservationRepository.findAllReservation();
    }

    public void finishReservation(int reservationId) {
        reservationRepository.finishReservation(reservationId);
    }

    public void createReservation(CreateReservationDto createReservationDto, int userId) {
        reservationRepository.createReservation(createReservationDto, userId);
    }

    public void joinReservation(int userId, JoinReservationReqDto joinReservationReqDto) {
        reservationRepository.joinReservation(userId, joinReservationReqDto);
    }

    public ReservationDto viewReservation(int reservationId) {
        return reservationRepository.viewReservation(reservationId);
    }
}
