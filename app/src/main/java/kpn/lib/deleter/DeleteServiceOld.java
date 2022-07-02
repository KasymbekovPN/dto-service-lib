package kpn.lib.deleter;

// TODO: del
public interface DeleteServiceOld<I, R> {
    R byId(I id);
    R all();
}
