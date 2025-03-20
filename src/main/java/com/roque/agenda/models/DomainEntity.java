package com.roque.agenda.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class DomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public DomainEntity() {}

    public DomainEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
