package kpn.lib.collection;

import java.util.Iterator;

public interface Collection<T> {
    T getFirst();
    Iterator<T> iterator();
}
