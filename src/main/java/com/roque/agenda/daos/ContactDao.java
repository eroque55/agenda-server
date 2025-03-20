package com.roque.agenda.daos;

import com.roque.agenda.models.Contact;
import com.roque.agenda.models.DomainEntity;
import com.roque.agenda.utils.Hibernate;

import java.util.List;

public class ContactDao implements IDao {
    @Override
    public DomainEntity create(DomainEntity entity) {
        Contact contact = (Contact) entity;
        try {
            Hibernate.getSessionFactory().inTransaction(session -> {
                session.persist(contact);
            });
            return contact;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DomainEntity read(DomainEntity entity) {
        Contact contact = (Contact) entity;
        try {
            Hibernate.getSessionFactory().inTransaction(session -> {
                Contact contactDB = session.get(Contact.class, contact.getId());

                if(contactDB != null) {
                    contact.setType(contactDB.getType());
                    contact.setValue(contactDB.getValue());
                    contact.setCustomer(contactDB.getCustomer());
                    contact.setObservations(contactDB.getObservations());
                }
            });
            return contact;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DomainEntity update(DomainEntity entity) {
        return null;
    }

    @Override
    public boolean delete(DomainEntity entity) {
        return false;
    }

    @Override
    public List<DomainEntity> listAll(DomainEntity entity) {
        return List.of();
    }
}
