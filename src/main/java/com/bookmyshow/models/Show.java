package com.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="shows")
public class Show extends BaseModel{

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="screen_id")
    private Screen screen;

    private Date startTime;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Features> features;


}
