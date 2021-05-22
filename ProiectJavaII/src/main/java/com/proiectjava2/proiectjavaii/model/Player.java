package com.proiectjava2.proiectjavaii.model;

import javax.persistence.*;

@Entity
public class Player {
    private String name;
    private Long id;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
