package kpn.lib.services.parts.loading.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

public final class DefaultLoadingAllExecutor<D> implements LoadingAllExecutor<D> {
    
    @Override
    public Collection<D> load() throws DTOServiceException {
        throw new DTOServiceException("executor.loadingAll.method.load.unsupported");
    }
}
