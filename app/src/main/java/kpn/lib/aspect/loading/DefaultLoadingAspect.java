package kpn.lib.aspect.loading;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.loading.CompletelyLoadingExecutor;
import kpn.lib.executor.loading.DefaultCompletelyLoadingExecutor;
import kpn.lib.executor.loading.DefaultByIdLoadingExecutor;
import kpn.lib.executor.loading.ByIdLoadingExecutor;

public class DefaultLoadingAspect<I, D extends Domain<?>> implements LoadingAspect<I, AspectResult<D>> {
    private final ByIdLoadingExecutor<I, D> byIdLoadingExecutor;
    private final CompletelyLoadingExecutor<D> completelyLoadingExecutor;
    
    public DefaultLoadingAspect(ByIdLoadingExecutor<I, D> byIdLoadingExecutor,
                                CompletelyLoadingExecutor<D> completelyLoadingExecutor) {
        this.byIdLoadingExecutor = byIdLoadingExecutor != null
            ? byIdLoadingExecutor
            : new DefaultByIdLoadingExecutor<>();
        this.completelyLoadingExecutor = completelyLoadingExecutor != null
            ? completelyLoadingExecutor
            : new DefaultCompletelyLoadingExecutor<>();
    }

    @Override
    public AspectResult<D> byId(I id) {
        try {
            return new DefaultAspectResult<>(byIdLoadingExecutor.load(id));
        } catch (DTOException e) {
            return new DefaultAspectResult<>(e.getMessage(), e.getArgs());
        }
    }

    @Override
    public AspectResult<D> all() {
        try {
            return new DefaultAspectResult<>(completelyLoadingExecutor.load());
        } catch (DTOException e) {
            return new DefaultAspectResult<>(e.getMessage(), e.getArgs());
        }
    }
}
