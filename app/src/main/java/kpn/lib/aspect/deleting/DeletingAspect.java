package kpn.lib.aspect.deleting;

public interface DeletingAspect<I,R> {
    R byId(I id);
    R all();
}
