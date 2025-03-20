package com.roque.agenda.models;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer", schema = "agenda")
public class Customer extends DomainEntity {
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column
    private LocalDate birthday;

    @Column
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Contact> contacts;

    public Customer() {
    }

    public Customer(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public Customer(int id, String name, String cpf, LocalDate birthday, String address, List<Contact> contacts) {
        super(id);
        this.name = name;
        this.cpf = cpf;
        this.birthday = birthday;
        this.address = address;
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
