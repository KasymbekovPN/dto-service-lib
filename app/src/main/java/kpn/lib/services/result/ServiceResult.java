package kpn.lib.services.result;

import kpn.lib.collection.Collection;

// TODO: rename
public interface ServiceResult<D> {
    boolean isSuccess();
    Collection<D> getCollection();
    String getCode();
    String[] getArgs();
}
