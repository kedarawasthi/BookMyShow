package com.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Theatre extends BaseModel{
    private String name;
    private String address;

    @OneToMany
    @JoinColumn(name="theatre_id")
    private List<Screen> screens;
}
