package com.roque.agenda.facades;

import com.roque.agenda.daos.ContactDao;
import com.roque.agenda.daos.CustomerDao;
import com.roque.agenda.daos.IDao;
import com.roque.agenda.models.Contact;
import com.roque.agenda.models.Customer;
import com.roque.agenda.models.DomainEntity;
import com.roque.agenda.strategies.IStrategy;
import com.roque.agenda.strategies.ValidateContact;
import com.roque.agenda.strategies.ValidateCustomer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade implements IFacade {
    private Map<String, IDao> daos;
    private Map<String, List<IStrategy>> strategies;

    public Facade() {
        setStrategies();
        setDaos();
    }

    private void setStrategies() {
        strategies = new HashMap<String, List<IStrategy>>();

        ValidateCustomer validateCustomer = new ValidateCustomer();
        ValidateContact validateContact = new ValidateContact();

        List<IStrategy> strategiesCustomer = new ArrayList<IStrategy>();
        strategiesCustomer.add(validateCustomer);

        List<IStrategy> strategiesContact = new ArrayList<IStrategy>();
        strategiesContact.add(validateContact);

        strategies.put(Customer.class.getName(), strategiesCustomer);
        strategies.put(Contact.class.getName(), strategiesContact);
    }

    private void setDaos() {
        daos = new HashMap<String, IDao>();
        daos.put(Customer.class.getName(), new CustomerDao());
        daos.put(Contact.class.getName(), new ContactDao());
    }

    public String execute(DomainEntity entity) {
        String className = entity.getClass().getName();

        List<IStrategy> strategyEntity = strategies.get(className);

        StringBuilder sb = new StringBuilder();
        for (IStrategy strategy : strategyEntity) {
            String msg = strategy.execute(entity);
            if (msg != null) {
                sb.append(msg);
            }
        }

        if (!sb.isEmpty()) {
            return sb.toString();
        } else {
            return null;
        }
    }

    @Override
    public DomainEntity create(DomainEntity entity) {
        String key = entity.getClass().getName();
        String msg = execute(entity);

        if (msg == null) {
            IDao dao = daos.get(key);
            return dao.create(entity);
        } else {
            throw new RuntimeException(msg);
        }
    }

    @Override
    public DomainEntity read(DomainEntity entity) {
        String key = entity.getClass().getName();
        IDao dao = daos.get(key);
        return dao.read(entity);
    }

    @Override
    public DomainEntity update(DomainEntity entity) {
        String key = entity.getClass().getName();
        IDao dao = daos.get(key);
        return dao.update(entity);
    }

    @Override
    public boolean delete(DomainEntity entity) {
        String key = entity.getClass().getName();
        IDao dao = daos.get(key);
        return dao.delete(entity);
    }

    @Override
    public List<DomainEntity> listAll(DomainEntity entity) {
        String key = entity.getClass().getName();
        IDao dao = daos.get(key);
        return dao.listAll(entity);
    }
}
