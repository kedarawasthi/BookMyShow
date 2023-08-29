package com.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
}
