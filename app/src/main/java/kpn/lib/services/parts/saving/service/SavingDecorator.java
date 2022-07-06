package kpn.lib.services.parts.saving.service;

import java.util.function.Function;

import kpn.lib.code.Code;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;

public final class SavingDecorator<D, R> implements SavingService<D, R> {
    private final SavingService<D, ServiceResult<D>> saver;
    private final Function<ServiceResult<D>, R> converter;
    
    public SavingDecorator(SavingService<D, ServiceResult<D>> saver,
                           Function<ServiceResult<D>, R> converter) {
        this.saver = saver;
        this.converter = converter;
    }

    @Override
    public R save(D domain) {
        return converter.apply(
            saver != null
                ? saver.save(domain)
                : new ImmutableServiceResult<>(Code.SERVICE_SAVING_UNSUPPORTED.getValue())
        );
    }
}
