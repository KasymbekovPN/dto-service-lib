package kpn.lib.services.parts.deleting.service;

import kpn.lib.exceptions.DTOServiceException;
import kpn.lib.services.parts.deleting.executor.DefaultDeletingAllExecutor;
import kpn.lib.services.parts.deleting.executor.DefaultDeletingByIdExecutor;
import kpn.lib.services.parts.deleting.executor.DeletingAllExecutor;
import kpn.lib.services.parts.deleting.executor.DeletingByIdExecutor;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;

public class SimpleDeletingService<I, D> implements DeletingService<I, ServiceResult<D>> {
    private final DeletingByIdExecutor<I,D> byIdExecutor;
    private final DeletingAllExecutor<D> allExecutor;

    public SimpleDeletingService(DeletingByIdExecutor<I, D> byIdExecutor, DeletingAllExecutor<D> allExecutor) {
        this.byIdExecutor = byIdExecutor != null ? byIdExecutor : new DefaultDeletingByIdExecutor<>();
        this.allExecutor = allExecutor != null ? allExecutor : new DefaultDeletingAllExecutor<>();
    }

    @Override
    public ServiceResult<D> byId(I id) {
        try {
            return new ImmutableServiceResult<>(byIdExecutor.delete(id));
        } catch (DTOServiceException e) {
            return new ImmutableServiceResult<>(e.getMessage(), e.getArgs());
        }
    }

    @Override
    public ServiceResult<D> all() {
        try {
            return new ImmutableServiceResult<>(allExecutor.delete());
        } catch (DTOServiceException e) {
            return new ImmutableServiceResult<>(e.getMessage(), e.getArgs());
        }
    }
}
