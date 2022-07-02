package kpn.lib.services.parts.loading.service;

import kpn.lib.exceptions.DTOServiceException;
import kpn.lib.services.parts.loading.executor.DefaultLoadingAllExecutor;
import kpn.lib.services.parts.loading.executor.DefaultLoadingByIdExecutor;
import kpn.lib.services.parts.loading.executor.LoadingAllExecutor;
import kpn.lib.services.parts.loading.executor.LoadingByIdExecutor;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;

public class SimpleLoadingService<I, D> implements LoadingService<I, ServiceResult<D>> {
    private final LoadingByIdExecutor<I, D> byIdExecutor;
    private final LoadingAllExecutor<D> allExecutor;
    
    public SimpleLoadingService(LoadingByIdExecutor<I, D> byIdExecutor, LoadingAllExecutor<D> allExecutor) {
        this.byIdExecutor = byIdExecutor != null ? byIdExecutor : new DefaultLoadingByIdExecutor<>();
        this.allExecutor = allExecutor != null ? allExecutor : new DefaultLoadingAllExecutor<>();
    }

    @Override
    public ServiceResult<D> byId(I id) {
        try {
            return new ImmutableServiceResult<>(byIdExecutor.load(id));
        } catch (DTOServiceException e) {
            return new ImmutableServiceResult<>(e.getMessage(), e.getArgs());
        }
    }

    @Override
    public ServiceResult<D> all() {
        try {
            return new ImmutableServiceResult<>(allExecutor.load());
        } catch (DTOServiceException e) {
            return new ImmutableServiceResult<>(e.getMessage(), e.getArgs());
        }
    }
}
