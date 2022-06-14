package kpn.lib.deleter;

import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface DeleterAll {
    void delete() throws DTOServiceException;
}
