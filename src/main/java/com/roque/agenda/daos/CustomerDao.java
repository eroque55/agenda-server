package com.roque.agenda.daos;

import com.roque.agenda.models.Customer;
import com.roque.agenda.models.DomainEntity;
import com.roque.agenda.utils.Hibernate;


import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements IDao {
    @Override
    public DomainEntity create(DomainEntity entity) {
        Customer customer = (Customer) entity;
        try {
            Hibernate.getSessionFactory().inTransaction(session -> {
                session.persist(customer);
                session.flush();
            });
            return customer;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DomainEntity read(DomainEntity entity) {
        Customer customer = (Customer) entity;
        try {
            Hibernate.getSessionFactory().inTransaction(session -> {
                Customer customerDB = session.get(Customer.class, customer.getId());

                if (customerDB != null) {
                    customer.setName(customerDB.getName());
                    customer.setCpf(customerDB.getCpf());
                    customer.setAddress(customerDB.getAddress());
                    customer.setBirthday(customerDB.getBirthday());
                    customer.setContacts(customerDB.getContacts());
                }
            });
            return customer;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DomainEntity update(DomainEntity entity) {
        Customer customer = (Customer) entity;
        try {
            Hibernate.getSessionFactory().inTransaction(session -> {
                Customer customerDB = session.get(Customer.class, customer.getId());

                if (customerDB == null) {
                    throw new RuntimeException("Cliente não encontrado");
                }

                if (customer.getName() != null) {
                    customerDB.setName(customer.getName());
                }
                if (customer.getCpf() != null) {
                    customerDB.setCpf(customer.getCpf());
                }
                if (customer.getAddress() != null) {
                    customerDB.setAddress(customer.getAddress());
                }
                if (customer.getBirthday() != null) {
                    customerDB.setBirthday(customer.getBirthday());
                }

                session.merge(customerDB);
                session.flush();

                customer.setName(customerDB.getName());
                customer.setCpf(customerDB.getCpf());
                customer.setAddress(customerDB.getAddress());
                customer.setBirthday(customerDB.getBirthday());
            });

            return customer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(DomainEntity entity) {
        Customer customer = (Customer) entity;
        try {
            Hibernate.getSessionFactory().inTransaction(session -> {
                Customer customerDB = session.get(Customer.class, customer.getId());
                session.remove(customerDB);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<DomainEntity> listAll(DomainEntity entity) {
        List<DomainEntity> customers = new ArrayList<>();
        try {
            Hibernate.getSessionFactory().inTransaction(session -> {
                customers.addAll(session.createQuery("from Customer", Customer.class).getResultList());
            });
        } catch (Exception e) {
            return null;
        }
        return customers;
    }
}
