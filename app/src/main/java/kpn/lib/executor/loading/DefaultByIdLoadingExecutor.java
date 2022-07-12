package kpn.lib.executor.loading;

import kpn.lib.code.ErrorCode;
import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

public final class DefaultByIdLoadingExecutor<I, D extends Domain<?>> implements ByIdLoadingExecutor<I, D> {
    
    @Override
    public ExecutorResult<D> load(I id) throws DTOException {
        throw new DTOException(ErrorCode.EXECUTOR_LOADING_BY_ID_UNSUPPORTED);
    }
}
