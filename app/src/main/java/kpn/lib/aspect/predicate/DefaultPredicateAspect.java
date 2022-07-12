package kpn.lib.aspect.predicate;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.domain.Domain;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.predicate.DefaultPredicateExecutor;
import kpn.lib.executor.predicate.PredicateExecutor;

public class DefaultPredicateAspect<P, D extends Domain<?>> implements PredicateAspect<P, AspectResult<D>> {
    private final PredicateExecutor<P, D> executor;
    
    public DefaultPredicateAspect(PredicateExecutor<P, D> executor) {
        this.executor = executor != null ? executor : new DefaultPredicateExecutor<>();
    }

    @Override
    public AspectResult<D> execute(P predicate) {
        try {
            return new DefaultAspectResult<>(executor.execute(predicate));
        } catch (DTOException e) {
            return new DefaultAspectResult<>(e.getMessage(), e.getArgs());
        }
    }
}
