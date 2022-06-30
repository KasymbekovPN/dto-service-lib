package kpn.lib.services.parts.deleting.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

public final class DefaultDeletingByIdExecutor<I, D> implements DeletingByIdExecutor<I, D> {
    
    @Override
    public Collection<D> delete(I id) throws DTOServiceException {
        throw new DTOServiceException("executor.deletingById.method.delete.unsupported");
    }
}
