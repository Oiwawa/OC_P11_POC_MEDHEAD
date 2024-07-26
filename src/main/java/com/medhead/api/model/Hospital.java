package com.medhead.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="hospital_name")
    private String name;

    private float longitude;

    private float latitude;

    @Column(name="spec")
    private String specialities;

    private String address;

    private Integer available_beds;
}