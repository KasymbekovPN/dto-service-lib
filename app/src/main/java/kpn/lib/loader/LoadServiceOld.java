package kpn.lib.loader;

// TODO: del
public interface LoadServiceOld<I, R> {
    R byId(I id);
    R all();
}
