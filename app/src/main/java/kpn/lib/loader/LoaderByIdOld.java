package kpn.lib.loader;

import kpn.lib.collection.Collection;
import kpn.lib.entities.Entity;
import kpn.lib.exceptions.DTOServiceException;

// TODO: del
@FunctionalInterface
public interface LoaderByIdOld<I, E extends Entity<I>> {
    Collection<E> load(I id) throws DTOServiceException;
}
