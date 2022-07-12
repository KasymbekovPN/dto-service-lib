package kpn.lib.aspect;

import kpn.lib.domain.Domain;
import kpn.lib.executor.ExecutorResult;

public interface AspectResult<D extends Domain<?>> {
    boolean isSuccess();
    ExecutorResult<D> getExecutorResult();
    String getCode();
    String[] getArgs();
}
