package com.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ShowSeat extends BaseModel{

    @ManyToOne
    @JoinColumn(name= "show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name= "seat_id")
    private Seat seat;

    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
}
