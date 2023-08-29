package com.bookmyshow.services;

import com.bookmyshow.models.ShowSeat;
import com.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSeatService {

    private ShowSeatRepository showSeatRepository;

    @Autowired
    public ShowSeatService(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    public List<ShowSeat> findByIdAndSeatStatus_Available(List<Integer> showSeatIds) {
        return showSeatRepository.findByIdInAndSeatStatus_Available(showSeatIds);
    }

    public void save(ShowSeat showSeat) {
        showSeatRepository.save(showSeat);
    }
}
