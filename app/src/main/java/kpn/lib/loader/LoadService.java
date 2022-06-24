package kpn.lib.loader;

public interface LoadService<I, R> {
    R byId(I id);
    R all();
}
