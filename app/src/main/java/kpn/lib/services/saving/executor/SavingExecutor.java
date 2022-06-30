package kpn.lib.services.saving.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface SavingExecutor<D> {
    Collection<D> save(D domain) throws DTOServiceException;
}