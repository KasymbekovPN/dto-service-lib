package kpn.lib.services.parts.deleting.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface DeletingByIdExecutor<I, D> {
    Collection<D> delete(I id) throws DTOServiceException;
}
