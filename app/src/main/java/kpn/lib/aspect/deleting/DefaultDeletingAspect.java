package kpn.lib.aspect.deleting;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.deleting.CompletelyDeletingExecutor;
import kpn.lib.executor.deleting.DefaultCompletelyDeletingExecutor;
import kpn.lib.executor.deleting.DefaultByIdDeletingExecutor;
import kpn.lib.executor.deleting.ByIdDeletingExecutor;

public class DefaultDeletingAspect<I, D extends Domain<?>> implements DeletingAspect<I, AspectResult<D>> {
    private final ByIdDeletingExecutor<I,D> byIdDeletingExecutor;
    private final CompletelyDeletingExecutor<D> completelyDeletingExecutor;

    public DefaultDeletingAspect(ByIdDeletingExecutor<I, D> byIdDeletingExecutor,
                                 CompletelyDeletingExecutor<D> completelyDeletingExecutor) {
        this.byIdDeletingExecutor = byIdDeletingExecutor != null 
            ? byIdDeletingExecutor
            : new DefaultByIdDeletingExecutor<>();
        this.completelyDeletingExecutor = completelyDeletingExecutor != null
            ? completelyDeletingExecutor
            : new DefaultCompletelyDeletingExecutor<>();
    }

    @Override
    public AspectResult<D> byId(I id) {
        try {
            return new DefaultAspectResult<>(byIdDeletingExecutor.delete(id));
        } catch (DTOException e) {
            return new DefaultAspectResult<>(e.getMessage(), e.getArgs());
        }
    }

    @Override
    public AspectResult<D> all() {
        try {
            return new DefaultAspectResult<>(completelyDeletingExecutor.delete());
        } catch (DTOException e) {
            return new DefaultAspectResult<>(e.getMessage(), e.getArgs());
        }
    }
}
