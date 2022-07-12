package kpn.lib.aspect.saving;

public interface SavingAspect<D, R> {
    R save(D domain);
}
