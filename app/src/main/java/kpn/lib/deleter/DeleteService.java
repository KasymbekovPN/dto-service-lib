package kpn.lib.deleter;

public interface DeleteService<R, I> {
    R byId(I id);
    R all();
}
