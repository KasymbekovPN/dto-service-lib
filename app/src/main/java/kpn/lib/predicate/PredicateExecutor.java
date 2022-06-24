package kpn.lib.predicate;

import kpn.lib.collection.DomainCollection;
import kpn.lib.domains.Domain;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface PredicateExecutor<P, I, D extends Domain<I>> {
    DomainCollection<D> execute(P predicate) throws DTOServiceException;
}
