package kpn.lib.aspect.predicate;

public interface PredicateAspect<P, R> {
    R execute(P predicate);
}
