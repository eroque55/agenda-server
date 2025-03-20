package com.roque.agenda.controllers;

import com.roque.agenda.models.DomainEntity;

import java.util.List;

public class Controller extends AbstractController{
    public DomainEntity create(DomainEntity entity){
        return facade.create(entity);
    }

    public DomainEntity read(DomainEntity entity){
        return facade.read(entity);
    };
    public DomainEntity update(DomainEntity entity){
        return facade.update(entity);
    };
    public boolean delete(DomainEntity entity){
        return facade.delete(entity);
    };
    public List<DomainEntity> listAll(DomainEntity entity){
        return facade.listAll(entity);
    };
}
