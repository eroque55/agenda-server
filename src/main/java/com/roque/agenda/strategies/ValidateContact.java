package com.roque.agenda.strategies;

import com.roque.agenda.models.Contact;
import com.roque.agenda.models.DomainEntity;

public class ValidateContact implements IStrategy{
    @Override
    public String execute(DomainEntity entity) {
        StringBuilder sb = new StringBuilder();
        if (entity == null) {
            sb.append("Entidade nula\n");
        }

        if (entity instanceof Contact) {
            Contact contact = (Contact) entity;

            if (contact.getType() == null){
                sb.append("Tipo de contato obrigatório\n");
            }
            if (contact.getValue() == null || contact.getValue().isEmpty()){
                sb.append("Valor do contato é obrigatório\n");
            }
        }

        return "";
    }
}
