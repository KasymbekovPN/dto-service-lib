package kpn.lib.saver;

import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface Saver<E> {
    E save(E entity) throws DTOServiceException;
}
