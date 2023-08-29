package com.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Ticket extends BaseModel{

    @OneToMany
    @JoinColumn(name= "ticket_id")
    private List<Seat> seats;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    private Date timeOfBooking;

    @ManyToOne
    @JoinColumn(name= "show_id")
    private Show show;

    private double price;





}
