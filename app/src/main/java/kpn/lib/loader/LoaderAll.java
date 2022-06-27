package kpn.lib.loader;

import kpn.lib.collection.Collection;
import kpn.lib.entities.Entity;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface LoaderAll<I, E extends Entity<I>> {
    Collection<E> load() throws DTOServiceException;
}
