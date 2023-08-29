package com.bookmyshow.repositories;

import com.bookmyshow.models.SeatTypeShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatTypeShowRepository extends JpaRepository<SeatTypeShow,Integer> {
    List<SeatTypeShow> findById(int show_id);
}
