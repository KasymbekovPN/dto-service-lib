package kpn.lib.services.parts.predicate.service;

import java.util.function.Function;

import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;

public final class PredicateDecorator<P, D, R> implements PredicateService<P, R> {
    private final PredicateService<P, ServiceResult<D>> service;
    private final Function<ServiceResult<D>, R> converter;

    public PredicateDecorator(PredicateService<P, ServiceResult<D>> service,
                              Function<ServiceResult<D>, R> converter) {
        this.service = service;
        this.converter = converter;
    }

    @Override
    public R execute(P predicate) {
        return converter.apply(
            service != null
                ? service.execute(predicate)
                : new ImmutableServiceResult<>("service.predicate.absent")
        );
    }
}
