package com.roque.agenda.daos;

import com.roque.agenda.models.Contact;
import com.roque.agenda.models.DomainEntity;
import com.roque.agenda.utils.Hibernate;

import java.util.ArrayList;
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
        Contact contact = (Contact) entity;
        try {
            Hibernate.getSessionFactory().inTransaction(session -> {
                Contact contactDB = session.get(Contact.class, contact.getId());

                if (contactDB == null) {
                    throw new RuntimeException("Contato não encontrado");
                }

                if (contact.getType() != null) {
                    contactDB.setType(contact.getType());
                }

                if (contact.getValue() != null) {
                    contactDB.setValue(contact.getValue());
                }

                if (contact.getObservations() != null) {
                    contactDB.setObservations(contact.getObservations());
                }

                session.merge(contactDB);
                session.flush();

                contact.setCustomer(contactDB.getCustomer());
                contact.setType(contactDB.getType());
                contact.setValue(contactDB.getValue());
                contact.setObservations(contactDB.getObservations());
            });

            return contact;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(DomainEntity entity) {
        Contact contact = (Contact) entity;
        try {
            Hibernate.getSessionFactory().inTransaction(session -> {
                Contact contactDB = session.get(Contact.class, contact.getId());
                session.remove(contactDB);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<DomainEntity> listAll(DomainEntity entity) {
        List<DomainEntity> contacts = new ArrayList<>();
        try {
            Hibernate.getSessionFactory().inTransaction(session -> {
                contacts.addAll(session.createQuery("from Contact", Contact.class).getResultList());
            });
        } catch (Exception e) {
            return null;
        }
        return contacts;
    }
}
