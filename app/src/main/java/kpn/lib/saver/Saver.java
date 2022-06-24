package kpn.lib.saver;

import kpn.lib.collection.DomainCollection;
import kpn.lib.domains.Domain;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface Saver<I, D extends Domain<I>> {
    DomainCollection<D> save(D domain) throws DTOServiceException;
}
