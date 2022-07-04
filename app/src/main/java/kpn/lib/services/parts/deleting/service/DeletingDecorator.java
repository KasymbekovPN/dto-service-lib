package kpn.lib.services.parts.deleting.service;

import java.util.function.Function;

import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;

public final class DeletingDecorator<I, D, R> implements DeletingService<I, R> {
    private final DeletingService<I, ServiceResult<D>> service;
    private final Function<ServiceResult<D>, R> converter;

    public DeletingDecorator(DeletingService<I, ServiceResult<D>> service,
                             Function<ServiceResult<D>, R> converter) {
        this.service = service;
        this.converter = converter;
    }

    @Override
    public R byId(I id) {
        return converter.apply(
            service != null
                ? service.byId(id)
                : new ImmutableServiceResult<>("service.deleting.byId.absent")
        );
    }

    @Override
    public R all() {
        return converter.apply(
            service != null
                ? service.all()
                : new ImmutableServiceResult<>("service.deleting.all.absent")
        );
    }
}
