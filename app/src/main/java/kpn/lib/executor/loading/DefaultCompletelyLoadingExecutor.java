package kpn.lib.executor.loading;

import kpn.lib.code.ErrorCode;
import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

public final class DefaultCompletelyLoadingExecutor<D extends Domain<?>> implements CompletelyLoadingExecutor<D> {
    
    @Override
    public ExecutorResult<D> load() throws DTOException {
        throw new DTOException(ErrorCode.EXECUTOR_LOADING_ALL_UNSUPPORTED);
    }
}
