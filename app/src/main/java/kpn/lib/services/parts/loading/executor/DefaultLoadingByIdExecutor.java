package kpn.lib.services.parts.loading.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

public final class DefaultLoadingByIdExecutor<I, D> implements LoadingByIdExecutor<I, D> {
    
    @Override
    public Collection<D> load(I id) throws DTOServiceException {
        throw new DTOServiceException("executor.loadingById.method.load.unsupported");
    }
}
