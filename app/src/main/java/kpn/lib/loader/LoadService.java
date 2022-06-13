package kpn.lib.loader;

public interface LoadService<R, L, I> {
    R byId(I id);
    L all();
}
