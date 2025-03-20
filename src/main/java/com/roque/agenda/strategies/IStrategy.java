package com.roque.agenda.strategies;

import com.roque.agenda.models.DomainEntity;

public interface IStrategy {
    public String execute(DomainEntity entity);
}
