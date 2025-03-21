package com.roque.agenda.daos;

import com.roque.agenda.models.Contact;
import com.roque.agenda.models.DomainEntity;
import com.roque.agenda.utils.Hibernateeeee;

import java.util.ArrayList;
import java.util.List;

public class ContactDao implements IDao {
    @Override
    public DomainEntity create(DomainEntity entity) {
        try {
            Contact contact = (Contact) entity;
            Hibernateeeee.getSessionFactory().inTransaction(session -> {
                session.persist(contact);
            });

            return contact;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public DomainEntity read(DomainEntity entity) {
        try {
            Contact contact = (Contact) entity;
            Hibernateeeee.getSessionFactory().inTransaction(session -> {
                Contact contactDB = session.get(Contact.class, contact.getId());

                if (contactDB == null) {
                    throw new RuntimeException("Contato não encontrado");
                }

                contact.setType(contactDB.getType());
                contact.setValue(contactDB.getValue());
                contact.setCustomer(contactDB.getCustomer());
                contact.setObservations(contactDB.getObservations());
            });
            return contact;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public DomainEntity update(DomainEntity entity) {
        try {
            Contact contact = (Contact) entity;
            Hibernateeeee.getSessionFactory().inTransaction(session -> {
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
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(DomainEntity entity) {
        try {
            Contact contact = (Contact) entity;
            Hibernateeeee.getSessionFactory().inTransaction(session -> {
                Contact contactDB = session.get(Contact.class, contact.getId());

                if (contactDB == null) {
                    throw new RuntimeException("Contato não encontrado");
                }

                session.remove(contactDB);
            });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<DomainEntity> listAll(DomainEntity entity) {
        try {
            List<DomainEntity> contacts = new ArrayList<>();

            Hibernateeeee.getSessionFactory().inTransaction(session -> {
                contacts.addAll(session.createQuery("from Contact", Contact.class).getResultList());
            });

            return contacts;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
