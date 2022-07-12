package kpn.lib.executor.deleting;

import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

@FunctionalInterface
public interface CompletelyDeletingExecutor<D extends Domain<?>> {
    ExecutorResult<D> delete() throws DTOException;
}
