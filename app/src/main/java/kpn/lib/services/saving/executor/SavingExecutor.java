package kpn.lib.services.saving.executor;

import kpn.lib.collection.Collection;
import kpn.lib.entities.Entity;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface SavingExecutor<I, E extends Entity<I>> {
    Collection<E> save(E entity) throws DTOServiceException;
}