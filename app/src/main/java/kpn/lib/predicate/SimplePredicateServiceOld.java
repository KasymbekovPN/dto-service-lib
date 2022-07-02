package kpn.lib.predicate;

import kpn.lib.collection.Collection;
import kpn.lib.converter.EDConverter;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.Domain;
import kpn.lib.entities.Entity;
import kpn.lib.exceptions.DTOServiceException;

// TODO: del
public class SimplePredicateServiceOld<I, E extends Entity<I>, D extends Domain<I>, P, R> implements PredicateServiceOld<P, R> {
    private final PredicateExecutorOld<P, I, E> executor;
    private final EDConverter<I, D, E> edConverter;
    private final MultiConverter<Collection<D>, R> toResultConverter;

    public SimplePredicateServiceOld(PredicateExecutorOld<P, I, E> executor,
                                  EDConverter<I, D, E> edConverter,
                                  MultiConverter<Collection<D>, R> toResultConverter) {
        this.executor = executor;
        this.edConverter = edConverter;
        this.toResultConverter = toResultConverter;
    }

    @Override
    public R execute(P predicate) {
        try {
            return toResultConverter.convertValue(
                edConverter.toDomains(
                    executor.execute(predicate)
                )
            );
        } catch (DTOServiceException e) {
            return toResultConverter.convertException(e);
        }
    }
}
