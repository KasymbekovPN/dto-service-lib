package kpn.lib.aspect.saving;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.saving.DefaultSavingExecutor;
import kpn.lib.executor.saving.SavingExecutor;

public class DefaultSavingAspect<D extends Domain<?>> implements SavingAspect<D, AspectResult<D>> {
    private SavingExecutor<D> executor;

    public DefaultSavingAspect(SavingExecutor<D> executor) {
        this.executor = executor != null ? executor : new DefaultSavingExecutor<>();
    }

    @Override
    public AspectResult<D> save(D domain) {
        try {
            return new DefaultAspectResult<>(executor.save(domain));
        } catch (DTOException e) {
            return new DefaultAspectResult<>(e.getMessage(), e.getArgs());
        }
    }
}