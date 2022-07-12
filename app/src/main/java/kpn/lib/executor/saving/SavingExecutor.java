package kpn.lib.executor.saving;

import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.ExecutorResult;

@FunctionalInterface
public interface SavingExecutor<D extends Domain<?>> {
    ExecutorResult<D> save(D domain) throws DTOException;
}