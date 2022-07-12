package kpn.lib.executor.deleting;

import kpn.lib.code.ErrorCode;
import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

public final class DefaultByIdDeletingExecutor<I, D extends Domain<?>> implements ByIdDeletingExecutor<I, D> {
    
    @Override
    public ExecutorResult<D> delete(I id) throws DTOException {
        throw new DTOException(ErrorCode.EXECUTOR_DELETING_BY_ID_UNSUPPORTED);
    }
}
