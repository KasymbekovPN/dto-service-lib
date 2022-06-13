package kpn.lib.loader;

import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface LoaderById<E, I> {
    E load(I id) throws DTOServiceException;
}
