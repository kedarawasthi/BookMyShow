package com.bookmyshow.services;

import com.bookmyshow.models.SeatTypeShow;
import com.bookmyshow.repositories.SeatTypeShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatTypeShowService {

    private SeatTypeShowRepository seatTypeShowRepository;

    @Autowired
    public SeatTypeShowService(SeatTypeShowRepository seatTypeShowRepository) {
        this.seatTypeShowRepository = seatTypeShowRepository;
    }

    public List<SeatTypeShow> findByShow(int showId) {
        return seatTypeShowRepository.findById(showId);
    }
}
