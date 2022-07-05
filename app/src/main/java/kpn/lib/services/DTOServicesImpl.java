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

    public static <I, D, P, R> Builder<I, D, P, R> buider(Function<ServiceResult<D>, R> converter){
        return new Builder<>(converter);
    }

    private DTOServicesImpl(SavingService<D, ServiceResult<D>> saver, 
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

    public static class Builder<I, D, P, R>{
        private final Function<ServiceResult<D>, R> converter;

        private SavingService<D, ServiceResult<D>> saver;
        private LoadingService<I, ServiceResult<D>> loader;
        private DeletingService<I, ServiceResult<D>> deleter;
        private PredicateService<P, ServiceResult<D>> executor;

        public Builder(Function<ServiceResult<D>, R> converter) {
            this.converter = converter;
        }

        public Builder<I,D,P,R> saver(SavingService<D, ServiceResult<D>> saver){
            this.saver = saver;
            return this;
        }

        public Builder<I,D,P,R> loader(LoadingService<I, ServiceResult<D>> loader){
            this.loader = loader;
            return this;
        }

        public Builder<I,D,P,R> deleter(DeletingService<I, ServiceResult<D>> deleter){
            this.deleter = deleter;
            return this;
        }

        public Builder<I,D,P,R> executor(PredicateService<P, ServiceResult<D>> executor){
            this.executor = executor;
            return this;
        }

        public DTOServices<I, D, P, R> build(){
            return new DTOServicesImpl<>(saver, loader, deleter, executor, converter);
        }
    }
}
