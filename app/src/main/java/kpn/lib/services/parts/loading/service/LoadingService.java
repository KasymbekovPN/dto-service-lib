package kpn.lib.services.parts.loading.service;

public interface LoadingService<I, R> {
    R byId(I id);
    R all();
}
