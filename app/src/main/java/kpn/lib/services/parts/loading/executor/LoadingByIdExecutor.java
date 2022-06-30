package kpn.lib.services.parts.loading.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface LoadingByIdExecutor<I, D> {
    Collection<D> load(I id) throws DTOServiceException;
}
