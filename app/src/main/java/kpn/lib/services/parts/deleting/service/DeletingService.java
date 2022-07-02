package kpn.lib.services.parts.deleting.service;

public interface DeletingService<I,R> {
    R byId(I id);
    R all();
}
