package kpn.lib.predicate;

import java.util.List;

import kpn.lib.converter.MultiConverter;
import kpn.lib.exceptions.DTOServiceException;

public class SimplePredicateService<R, P, E> implements PredicateService<R, P> {
    private final PredicateExecutor<P, E> executor;
    private final MultiConverter<List<E>, R> converter;

    public SimplePredicateService(PredicateExecutor<P, E> executor,
                                  MultiConverter<List<E>, R> converter) {
        this.executor = executor;
        this.converter = converter;
    }

    @Override
    public R execute(P predicate) {
        try {
            List<E> result = executor.execute(predicate);
            return converter.convertValue(result);
        } catch (DTOServiceException e) {
            return converter.convertException(e);
        }
    }
}
