package kpn.lib.saver;

public interface SaveService<E, R> {
    R save(E entity);
}
