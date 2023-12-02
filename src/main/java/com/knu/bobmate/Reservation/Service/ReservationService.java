package com.knu.bobmate.Reservation.Service;

import com.knu.bobmate.Reservation.Dto.ReservationResDto;
import com.knu.bobmate.Reservation.Repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
}
