package kpn.lib.loader;

import java.util.List;

import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface LoaderAll<E> {
    List<E> load() throws DTOServiceException;
}
