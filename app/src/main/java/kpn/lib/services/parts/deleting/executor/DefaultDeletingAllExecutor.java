package kpn.lib.services.parts.deleting.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

public final class DefaultDeletingAllExecutor<D> implements DeletingAllExecutor<D>{

    @Override
    public Collection<D> delete() throws DTOServiceException {
        throw new DTOServiceException("executor.deletingAll.method.delete.unsupported");
    }
}
