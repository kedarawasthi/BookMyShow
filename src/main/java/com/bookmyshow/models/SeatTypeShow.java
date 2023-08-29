package com.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SeatTypeShow extends BaseModel{


    @Enumerated(EnumType.ORDINAL)
    public SeatType seatType;

    @ManyToOne
    @JoinColumn(name= "show_id")
    private Show show;

    private double price;
}
