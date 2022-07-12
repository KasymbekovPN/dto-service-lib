package kpn.lib.executor.predicate;

import kpn.lib.code.ErrorCode;
import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

public final class DefaultPredicateExecutor<P,D extends Domain<?>> implements PredicateExecutor<P,D> {
    
    @Override
    public ExecutorResult<D> execute(P predicate) throws DTOException {
        throw new DTOException(ErrorCode.EXECUTOR_PREDICATE_UNSUPPORTED);
    }
}
