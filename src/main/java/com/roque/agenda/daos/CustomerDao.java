package com.roque.agenda.daos;

import com.roque.agenda.models.Customer;
import com.roque.agenda.models.DomainEntity;
import com.roque.agenda.utils.Hibernateeeee;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements IDao {
    @Override
    public DomainEntity create(DomainEntity entity) {
        try {
            Customer customer = (Customer) entity;
            Hibernateeeee.getSessionFactory().inTransaction(session -> {
                session.persist(customer);
                session.flush();
            });

            return customer;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public DomainEntity read(DomainEntity entity) {
        Customer customer = (Customer) entity;
        try {
            Hibernateeeee.getSessionFactory().inTransaction(session -> {
                Customer customerDB = session.get(Customer.class, customer.getId());

                if (customerDB == null) {
                    throw new RuntimeException("Cliente não encontrado");
                }

                var graph = session.createEntityGraph(Customer.class);
                graph.addSubgraph(Customer().);
                graph.addPluralSubgraph(BCustomer.authors).addSubgraph(AutCustomer.person);
                Book book = entityManager.find(graph, bookId);

                Hibernate.initialize(customerDB.getContacts());

                customer.setName(customerDB.getName());
                customer.setCpf(customerDB.getCpf());
                customer.setAddress(customerDB.getAddress());
                customer.setBirthday(customerDB.getBirthday());
                customer.setContacts(customerDB.getContacts());
            });
            return customer;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public DomainEntity update(DomainEntity entity) {
        try {
            Customer customer = (Customer) entity;
            Hibernateeeee.getSessionFactory().inTransaction(session -> {
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
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(DomainEntity entity) {
        try {
            Customer customer = (Customer) entity;
            Hibernateeeee.getSessionFactory().inTransaction(session -> {
                Customer customerDB = session.get(Customer.class, customer.getId());

                if (customerDB == null) {
                    throw new RuntimeException("Cliente não encotrado");
                }

                session.remove(customerDB);
            });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<DomainEntity> listAll(DomainEntity entity) {
        try {
            List<DomainEntity> customers = new ArrayList<>();

            Hibernateeeee.getSessionFactory().inTransaction(session -> {
                customers.addAll(session.createQuery("from Customer", Customer.class).getResultList());
            });

            return customers;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
