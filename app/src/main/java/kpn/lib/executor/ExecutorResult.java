package kpn.lib.executor;

import java.util.Iterator;

import kpn.lib.domain.Domain;

public interface ExecutorResult<D extends Domain<?>> {
    D getFirst();
    Iterator<D> iterator();
}
