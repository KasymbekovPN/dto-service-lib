package kpn.lib.services.parts.predicate.service;

import kpn.lib.exceptions.DTOServiceException;
import kpn.lib.services.parts.predicate.executor.DefaultPredicateExecutor;
import kpn.lib.services.parts.predicate.executor.PredicateExecutor;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;

public class SimplePredicateService<P, D> implements PredicateService<P, ServiceResult<D>> {
    private final PredicateExecutor<P, D> executor;
    
    public SimplePredicateService(PredicateExecutor<P, D> executor) {
        this.executor = executor != null ? executor : new DefaultPredicateExecutor<>();
    }

    @Override
    public ServiceResult<D> execute(P predicate) {
        try {
            return new ImmutableServiceResult<>(executor.execute(predicate));
        } catch (DTOServiceException e) {
            return new ImmutableServiceResult<>(e.getMessage(), e.getArgs());
        }
    }
}
