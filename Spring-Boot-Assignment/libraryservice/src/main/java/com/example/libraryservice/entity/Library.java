package com.example.libraryservice.entity;


import jakarta.persistence.*;
import lombok.*;



import java.util.List;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;


public Library(){

}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public Library(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
}
