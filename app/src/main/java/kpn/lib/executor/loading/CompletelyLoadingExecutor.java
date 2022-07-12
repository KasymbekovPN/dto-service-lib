package kpn.lib.executor.loading;

import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

@FunctionalInterface
public interface CompletelyLoadingExecutor<D extends Domain<?>> {
    ExecutorResult<D> load() throws DTOException;
}
