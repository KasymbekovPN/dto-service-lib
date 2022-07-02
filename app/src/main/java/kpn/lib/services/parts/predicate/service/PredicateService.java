package kpn.lib.services.parts.predicate.service;

public interface PredicateService<P, R> {
    R execute(P predicate);
}
