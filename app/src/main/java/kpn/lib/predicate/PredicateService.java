package kpn.lib.predicate;

public interface PredicateService<P, R> {
    R execute(P predicate);
}
