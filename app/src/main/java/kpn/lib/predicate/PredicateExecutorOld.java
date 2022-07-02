package kpn.lib.predicate;

import kpn.lib.collection.Collection;
import kpn.lib.entities.Entity;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface PredicateExecutorOld<P, I, E extends Entity<I>> {
    Collection<E> execute(P predicate) throws DTOServiceException;
}
