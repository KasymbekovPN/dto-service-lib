package kpn.lib.services.parts.deleting.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface DeletingAllExecutor<D> {
    Collection<D> delete() throws DTOServiceException;
}
