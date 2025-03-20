package com.roque.agenda.daos;

import com.roque.agenda.models.DomainEntity;

import java.util.List;

public interface IDao {
    public DomainEntity create(DomainEntity entity);
    public DomainEntity read(DomainEntity entity);
    public DomainEntity update(DomainEntity entity);
    public boolean delete(DomainEntity entity);
    public List<DomainEntity> listAll(DomainEntity entity);
}
