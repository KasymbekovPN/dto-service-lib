package kpn.lib.saver;

import kpn.lib.collection.Collection;
import kpn.lib.entities.Entity;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface Saver<I, E extends Entity<I>> {
    Collection<E> save(E entity) throws DTOServiceException;
}