package com.roque.agenda.models;

import jakarta.persistence.*;

@Entity
@Table(name = "contact", schema = "agenda")
public class Contact extends DomainEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContactType type;

    @Column(length = 100,nullable = false)
    private String value;

    @Column
    private String observations;

    public Contact() {}

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Contact(int id, Customer customer, String value, String observations) {
        super(id);
        this.customer = customer;
        this.value = value;
        this.observations = observations;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
