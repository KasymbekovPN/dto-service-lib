package kpn.lib.executor.predicate;

import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

@FunctionalInterface
public interface PredicateExecutor<P, D extends Domain<?>> {
    ExecutorResult<D> execute(P predicate) throws DTOException;
}
