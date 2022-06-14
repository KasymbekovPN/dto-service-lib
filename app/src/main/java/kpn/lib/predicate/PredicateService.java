package kpn.lib.predicate;

public interface PredicateService<R, P> {
    R execute(P predicate);
}
