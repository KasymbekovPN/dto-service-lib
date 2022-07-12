package kpn.lib.executor.deleting;

import kpn.lib.code.ErrorCode;
import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

public final class DefaultCompletelyDeletingExecutor<D extends Domain<?>> implements CompletelyDeletingExecutor<D>{

    @Override
    public ExecutorResult<D> delete() throws DTOException {
        throw new DTOException(ErrorCode.EXECUTOR_DELETING_ALL_UNSUPPORTED);
    }
}
