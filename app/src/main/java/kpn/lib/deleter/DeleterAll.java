package kpn.lib.deleter;

import kpn.lib.collection.Collection;
import kpn.lib.entities.Entity;
import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface DeleterAll<I, E extends Entity<I>> {
    Collection<E> delete() throws DTOServiceException;
}
