package kpn.lib.deleter;

import kpn.lib.collection.DomainCollection;
import kpn.lib.domains.Domain;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface DeleterById<I, D extends Domain<I>> {
    DomainCollection<D> delete(I id) throws DTOServiceException;
}
