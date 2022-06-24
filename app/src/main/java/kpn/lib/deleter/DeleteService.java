package kpn.lib.deleter;

public interface DeleteService<I, R> {
    R byId(I id);
    R all();
}
