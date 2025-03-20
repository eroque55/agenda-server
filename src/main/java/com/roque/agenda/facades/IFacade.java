package com.roque.agenda.facades;

import com.roque.agenda.models.DomainEntity;

import java.util.List;

public interface IFacade {
    public DomainEntity create(DomainEntity entity);
    public DomainEntity read(DomainEntity entity);
    public DomainEntity update(DomainEntity entity);
    public DomainEntity delete(DomainEntity entity);
    public List<DomainEntity> listALl(DomainEntity entity);
}
