package com.example.codeclan.whiskytracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "whiskies")
public class Whisky {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int year;

    @Column
    private int age;

    @ManyToOne
    @JoinColumn(name = "distillery_id", nullable = false)
    @JsonIgnoreProperties({"whiskies"})
    private Distillery distillery;

    public Whisky(String name, int age,int year, Distillery distillery) {
        this.name = name;
        this.age = age;
        this.year = year;
        this.distillery = distillery;
    }

    public Whisky() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Distillery getDistillery() {
        return distillery;
    }

    public void setDistillery(Distillery distillery) {
        this.distillery = distillery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
