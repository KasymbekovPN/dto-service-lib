package kpn.lib.services;

import java.util.function.Function;

import kpn.lib.services.parts.deleting.service.DeletingDecorator;
import kpn.lib.services.parts.deleting.service.DeletingService;
import kpn.lib.services.parts.loading.service.LoadingDecorator;
import kpn.lib.services.parts.loading.service.LoadingService;
import kpn.lib.services.parts.predicate.service.PredicateDecorator;
import kpn.lib.services.parts.predicate.service.PredicateService;
import kpn.lib.services.parts.saving.service.SavingDecorator;
import kpn.lib.services.parts.saving.service.SavingService;
import kpn.lib.services.result.ServiceResult;

public class DTOServicesImpl<I, D, P, R> implements DTOServices<I, D, P, R> {
    private final SavingService<D, ServiceResult<D>> saver;
    private final LoadingService<I, ServiceResult<D>> loader;
    private final DeletingService<I, ServiceResult<D>> deleter;
    private final PredicateService<P, ServiceResult<D>> executor;
    private final Function<ServiceResult<D>, R> converter;

    public DTOServicesImpl(SavingService<D, ServiceResult<D>> saver, 
                           LoadingService<I, ServiceResult<D>> loader,
                           DeletingService<I, ServiceResult<D>> deleter,
                           PredicateService<P, ServiceResult<D>> executor,
                           Function<ServiceResult<D>, R> converter) {
        this.saver = saver;
        this.loader = loader;
        this.deleter = deleter;
        this.executor = executor;
        this.converter = converter;
    }

    @Override
    public SavingService<D, R> saver() {
        return new SavingDecorator<>(saver, converter);
    }

    @Override
    public LoadingService<I, R> loader() {
        return new LoadingDecorator<>(loader, converter);
    }

    @Override
    public DeletingService<I, R> deleter() {
        return new DeletingDecorator<>(deleter, converter);
    }

    @Override
    public PredicateService<P, R> executor() {
        return new PredicateDecorator<>(executor, converter);
    }
}
