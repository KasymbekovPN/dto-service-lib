package kpn.lib.executor.saving;

import kpn.lib.code.ErrorCode;
import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

public final class DefaultSavingExecutor<D extends Domain<?>> implements SavingExecutor<D>{

    @Override
    public ExecutorResult<D> save(D domain) throws DTOException {
        throw new DTOException(ErrorCode.EXECUTOR_SAVING_UNSUPPORTED);
    }
}
