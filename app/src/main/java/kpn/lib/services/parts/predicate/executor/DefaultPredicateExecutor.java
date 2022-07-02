package kpn.lib.services.parts.predicate.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

public final class DefaultPredicateExecutor<P,D> implements PredicateExecutor<P,D> {
    
    @Override
    public Collection<D> execute(P predicate) throws DTOServiceException {
        throw new DTOServiceException("executor.predicate.method.execute.unsupported");
    }
}
