package com.roque.agenda.daos;

import com.roque.agenda.models.Customer;
import com.roque.agenda.models.DomainEntity;
import com.roque.agenda.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements IDao {
    @Override
    public DomainEntity create(DomainEntity entity) {
        try {
            Customer customer = (Customer) entity;
            HibernateUtil.getSessionFactory().inTransaction(session -> {
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
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                Customer customerDB = session.get(Customer.class, customer.getId());

                if (customerDB == null) {
                    throw new RuntimeException("Cliente não encontrado");
                }

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
            HibernateUtil.getSessionFactory().inTransaction(session -> {
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
            HibernateUtil.getSessionFactory().inTransaction(session -> {
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
            Customer filter = (Customer) entity;
            StringBuilder query = new StringBuilder("from Customer c where 1=1");

            if (filter.getName() != null) query.append(" and c.name like '%" + filter.getName() + "%'");
            if (filter.getCpf() != null) query.append(" and c.cpf like '%" + filter.getCpf() + "%'");

            List<Customer> tempCustomers = new ArrayList<>();
            List<DomainEntity> customers = new ArrayList<>();
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                tempCustomers.addAll(session.createQuery(query.toString(), Customer.class).getResultList());
            });
            tempCustomers.forEach(customer -> {
                customer.setContacts(new ArrayList<>());
                customers.add(customer);
            });
            return customers;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
