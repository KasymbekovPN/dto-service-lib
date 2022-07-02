package kpn.lib.services.parts.saving.service;

import kpn.lib.exceptions.DTOServiceException;
import kpn.lib.services.parts.saving.executor.DefaultSavingExecutor;
import kpn.lib.services.parts.saving.executor.SavingExecutor;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;

public class SimpleSavingService<D> implements SavingService<D, ServiceResult<D>> {
    private SavingExecutor<D> executor;

    public SimpleSavingService(SavingExecutor<D> executor) {
        this.executor = executor != null ? executor : new DefaultSavingExecutor<>();
    }

    @Override
    public ServiceResult<D> save(D domain) {
        try {
            return new ImmutableServiceResult<>(executor.save(domain));
        } catch (DTOServiceException e) {
            return new ImmutableServiceResult<>(e.getMessage(), e.getArgs());
        }
    }
}