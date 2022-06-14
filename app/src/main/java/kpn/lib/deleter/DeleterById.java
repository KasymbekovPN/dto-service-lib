package kpn.lib.deleter;

import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface DeleterById<I> {
    void delete(I id) throws DTOServiceException;
}
