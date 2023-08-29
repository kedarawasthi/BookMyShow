package com.bookmyshow.services;

import com.bookmyshow.models.Seat;
import com.bookmyshow.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> findByIdIn(List<Integer> seatIds) {
        return seatRepository.findByIdIn(seatIds);
    }
}
