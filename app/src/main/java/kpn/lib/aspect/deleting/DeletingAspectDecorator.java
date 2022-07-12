package kpn.lib.aspect.deleting;

import java.util.function.Function;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.code.ErrorCode;
import kpn.lib.domain.Domain;

public final class DeletingAspectDecorator<I, D extends Domain<?>, R> implements DeletingAspect<I, R> {
    private final DeletingAspect<I, AspectResult<D>> aspect;
    private final Function<AspectResult<D>, R> converter;

    public DeletingAspectDecorator(DeletingAspect<I, AspectResult<D>> aspect,
                                   Function<AspectResult<D>, R> converter) {
        this.aspect = aspect;
        this.converter = converter;
    }

    @Override
    public R byId(I id) {
        return converter.apply(
            aspect != null
                ? aspect.byId(id)
                : new DefaultAspectResult<>(ErrorCode.SERVICE_DELETING_BY_ID_UNSUPPORTED.getValue())
        );
    }

    @Override
    public R all() {
        return converter.apply(
            aspect != null
                ? aspect.all()
                : new DefaultAspectResult<>(ErrorCode.SERVICE_DELETING_ALL_UNSUPPORTED.getValue())
        );
    }
}
