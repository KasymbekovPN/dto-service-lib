package kpn.lib.executor.loading;

import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

@FunctionalInterface
public interface ByIdLoadingExecutor<I, D extends Domain<?>> {
    ExecutorResult<D> load(I id) throws DTOException;
}
