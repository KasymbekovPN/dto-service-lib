package kpn.lib.services.parts.loading.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface LoadingAllExecutor<D> {
    Collection<D> load() throws DTOServiceException;
}
