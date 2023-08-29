package com.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Seat  extends BaseModel{
    private String name;
    private SeatType seatType;
    private int topLeftX;
    private int topLeftY;
    private int bottomRightX;
    private int bottomRightY;
}
