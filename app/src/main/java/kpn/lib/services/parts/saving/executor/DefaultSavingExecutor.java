package kpn.lib.services.parts.saving.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

public final class DefaultSavingExecutor<D> implements SavingExecutor<D>{

    @Override
    public Collection<D> save(D domain) throws DTOServiceException {
        throw new DTOServiceException("executor.save.unsupported");
    }
}
