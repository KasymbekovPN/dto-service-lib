package kpn.lib.aspect.loading;

public interface LoadingAspect<I, R> {
    R byId(I id);
    R all();
}
