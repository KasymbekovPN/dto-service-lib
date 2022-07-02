package kpn.lib.services.parts.predicate.executor;

import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface PredicateExecutor<P, D> {
    Collection<D> execute(P predicate) throws DTOServiceException;
}
