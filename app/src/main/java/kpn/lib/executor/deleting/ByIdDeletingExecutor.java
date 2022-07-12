package kpn.lib.executor.deleting;

import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

@FunctionalInterface
public interface ByIdDeletingExecutor<I, D extends Domain<?>> {
    ExecutorResult<D> delete(I id) throws DTOException;
}
