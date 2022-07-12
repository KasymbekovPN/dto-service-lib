package kpn.lib.aspect.saving;

import java.util.function.Function;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.code.ErrorCode;
import kpn.lib.domain.Domain;

public final class SavingAspectDecorator<D extends Domain<?>, R> implements SavingAspect<D, R> {
    private final SavingAspect<D, AspectResult<D>> aspect;
    private final Function<AspectResult<D>, R> converter;
    
    public SavingAspectDecorator(SavingAspect<D, AspectResult<D>> aspect,
                                 Function<AspectResult<D>, R> converter) {
        this.aspect = aspect;
        this.converter = converter;
    }

    @Override
    public R save(D domain) {
        return converter.apply(
            aspect != null
                ? aspect.save(domain)
                : new DefaultAspectResult<>(ErrorCode.SERVICE_SAVING_UNSUPPORTED.getValue())
        );
    }
}
