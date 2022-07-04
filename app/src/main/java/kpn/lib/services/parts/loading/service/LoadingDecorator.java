package kpn.lib.services.parts.loading.service;

import java.util.function.Function;

import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;

public final class LoadingDecorator<I, D, R> implements LoadingService<I, R> {
    private final LoadingService<I, ServiceResult<D>> service;
    private final Function<ServiceResult<D>, R> converter;

    public LoadingDecorator(LoadingService<I, ServiceResult<D>> service,
                            Function<ServiceResult<D>, R> converter) {
        this.service = service;
        this.converter = converter;
    }

    @Override
    public R byId(I id) {
        return converter.apply(
            service != null
                ? service.byId(id)
                : new ImmutableServiceResult<>("service.loading.byId.absent")
        );
    }

    @Override
    public R all() {
        return converter.apply(
            service != null
                ? service.all()
                : new ImmutableServiceResult<>("service.loading.all.absent")
        );
    }
}