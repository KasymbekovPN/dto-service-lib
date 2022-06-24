package kpn.lib.predicate;

import kpn.lib.collection.DomainCollection;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.Domain;
import kpn.lib.exceptions.DTOServiceException;

public class SimplePredicateService<I, D extends Domain<I>, P, R> implements PredicateService<P, R> {
    private final PredicateExecutor<P, I, D> executor;
    private final MultiConverter<DomainCollection<D>, R> converter;

    public SimplePredicateService(PredicateExecutor<P, I, D> executor,
                                  MultiConverter<DomainCollection<D>, R> converter) {
        this.executor = executor;
        this.converter = converter;
    }

    @Override
    public R execute(P predicate) {
        try {
            DomainCollection<D> collection = executor.execute(predicate);
            return converter.convertValue(collection);
        } catch (DTOServiceException e) {
            return converter.convertException(e);
        }
    }
}
