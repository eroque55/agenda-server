package com.roque.agenda.strategies;

import com.roque.agenda.models.Contact;
import com.roque.agenda.models.ContactType;
import com.roque.agenda.models.DomainEntity;

public class ValidateContact implements IStrategy {
    @Override
    public String execute(DomainEntity entity) {
        StringBuilder sb = new StringBuilder();

        if (entity == null) {
            sb.append("Entidade nula, ");
        }

        Contact contact = (Contact) entity;

        if (contact.getCustomer() == null) {
            sb.append("Cliente obrigatório, ");
        } else if (contact.getCustomer().getId() == 0) {
            sb.append("Cliente deve ter um id, ");
        }

        if (contact.getType() == null) {
            sb.append("Tipo de contato obrigatório; ");
        } else if (contact.getValue() == null || contact.getValue().isEmpty()) {
            sb.append("Valor do contato é obrigatório, ");
        } else if (contact.getType() == ContactType.EMAIL) {
            if (!isValidEmail(contact.getValue())) {
                sb.append("E-mail inválido, ");
            }
        } else if (contact.getType() == ContactType.PHONE) {
            if (!isValidPhone(contact.getValue())) {
                sb.append("Número de telefone inválido, ");
            }
        }

        return sb.toString();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    private boolean isValidPhone(String phone) {
        String phoneRegex = "^\\d{11}$";
        return phone != null && phone.matches(phoneRegex);
    }
}
