package kpn.lib.loader;

import kpn.lib.collection.DomainCollection;
import kpn.lib.domains.Domain;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface LoaderById<I, D extends Domain<I>> {
    DomainCollection<D> load(I id) throws DTOServiceException;
}
