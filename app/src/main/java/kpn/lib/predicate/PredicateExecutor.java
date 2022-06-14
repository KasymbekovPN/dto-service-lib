package kpn.lib.predicate;

import java.util.List;

import kpn.lib.exceptions.DTOServiceException;

@FunctionalInterface
public interface PredicateExecutor<P, E> {
    List<E> execute(P predicate) throws DTOServiceException;
}
