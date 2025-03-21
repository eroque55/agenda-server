package com.roque.agenda.facades;

import com.roque.agenda.models.DomainEntity;

import java.util.List;

public interface IFacade {
    DomainEntity create(DomainEntity entity);
    DomainEntity read(DomainEntity entity);
    DomainEntity update(DomainEntity entity);
    void delete(DomainEntity entity);
    List<DomainEntity> listAll(DomainEntity entity);
}
