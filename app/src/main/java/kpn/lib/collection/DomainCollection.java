package kpn.lib.collection;

import java.util.Iterator;

import kpn.lib.domains.Domain;

public interface DomainCollection<D extends Domain<?>> {
    D getFirst();
    Iterator<D> iterator();
}
