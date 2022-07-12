package kpn.lib.aspect.predicate;

import java.util.function.Function;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.code.ErrorCode;
import kpn.lib.domain.Domain;

public final class PredicateAspectDecorator<P, D extends Domain<?>, R> implements PredicateAspect<P, R> {
    private final PredicateAspect<P, AspectResult<D>> aspect;
    private final Function<AspectResult<D>, R> converter;

    public PredicateAspectDecorator(PredicateAspect<P, AspectResult<D>> aspect,
                                    Function<AspectResult<D>, R> converter) {
        this.aspect = aspect;
        this.converter = converter;
    }

    @Override
    public R execute(P predicate) {
        return converter.apply(
            aspect != null
                ? aspect.execute(predicate)
                : new DefaultAspectResult<>(ErrorCode.SERVICE_PREDICATE_UNSUPPORTED.getValue())
        );
    }
}
