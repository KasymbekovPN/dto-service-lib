package kpn.lib.services.builder;

import java.util.function.Function;

import kpn.lib.services.DTOServices;
import kpn.lib.services.DTOServicesImpl;
import kpn.lib.services.parts.deleting.executor.DeletingAllExecutor;
import kpn.lib.services.parts.deleting.executor.DeletingByIdExecutor;
import kpn.lib.services.parts.deleting.service.DeletingService;
import kpn.lib.services.parts.deleting.service.SimpleDeletingService;
import kpn.lib.services.parts.loading.executor.LoadingAllExecutor;
import kpn.lib.services.parts.loading.executor.LoadingByIdExecutor;
import kpn.lib.services.parts.loading.service.LoadingService;
import kpn.lib.services.parts.loading.service.SimpleLoadingService;
import kpn.lib.services.parts.predicate.executor.PredicateExecutor;
import kpn.lib.services.parts.predicate.service.PredicateService;
import kpn.lib.services.parts.predicate.service.SimplePredicateService;
import kpn.lib.services.parts.saving.executor.SavingExecutor;
import kpn.lib.services.parts.saving.service.SavingService;
import kpn.lib.services.parts.saving.service.SimpleSavingService;
import kpn.lib.services.result.ServiceResult;

public final class ServiceBuider<I, D, P, R> {

    private final Function<ServiceResult<D>, R> converter;

    private SavingService<D, ServiceResult<D>> saver;
    private LoadingService<I, ServiceResult<D>> loader;
    private DeletingService<I, ServiceResult<D>> deleter;
    private PredicateService<P, ServiceResult<D>> executor;

    public ServiceBuider(Function<ServiceResult<D>, R> converter) {
        this.converter = converter;
    }

    public DTOServices<I,D,P,R> build(){
        return new DTOServicesImpl<>(saver, loader, deleter, executor, converter);
    }

    public ServiceBuider<I,D,P,R> saver(SavingService<D, ServiceResult<D>> saver){
        this.saver = saver;
        return this;
    }

    public SaverBuidler<I,D,P,R> saverBuider(){
        return new SaverBuidler<>(this);
    }

    public ServiceBuider<I,D,P,R> loader(LoadingService<I, ServiceResult<D>> loader){
        this.loader = loader;
        return this;
    }

    public LoaderBuilder<I,D,P,R> loaderBuilder(){
        return new LoaderBuilder<>(this);
    }

    public ServiceBuider<I,D,P,R> deleter(DeletingService<I, ServiceResult<D>> deleter){
        this.deleter = deleter;
        return this;
    }

    public DeleterBuilder<I,D,P,R> deleterBuilder(){
        return new DeleterBuilder<>(this);
    }

    public ServiceBuider<I,D,P,R> predicateExecutor(PredicateService<P, ServiceResult<D>> executor){
        this.executor = executor;
        return this;
    }

    public PredicateExecutorBuidler<I,D,P,R> predicateExecutorBuidler(){
        return new PredicateExecutorBuidler<>(this);
    }

    public static class SaverBuidler<I, D, P, R>{
        private final ServiceBuider<I, D, P, R> serviceBuider;

        private SavingExecutor<D> executor;

        public SaverBuidler(ServiceBuider<I, D, P, R> serviceBuider) {
            this.serviceBuider = serviceBuider;
        }

        public SaverBuidler<I, D, P, R> executor(SavingExecutor<D> executor){
            this.executor = executor;
            return this;
        }

        public ServiceBuider<I, D, P, R> build(){
            serviceBuider.saver(new SimpleSavingService<>(executor));
            return serviceBuider;
        }
    }

    public static class LoaderBuilder<I, D, P, R>{
        private final ServiceBuider<I, D, P, R> serviceBuider;

        private LoadingAllExecutor<D> executorAll;
        private LoadingByIdExecutor<I, D> executorById;

        public LoaderBuilder(ServiceBuider<I, D, P, R> serviceBuider) {
            this.serviceBuider = serviceBuider;
        }

        public LoaderBuilder<I,D,P,R> executorAll(LoadingAllExecutor<D> executor){
            executorAll = executor;
            return this;
        }

        public LoaderBuilder<I,D,P,R> executorById(LoadingByIdExecutor<I, D> executor){
            executorById = executor;
            return this;
        }

        public ServiceBuider<I, D, P, R> build(){
            serviceBuider.loader(new SimpleLoadingService<>(executorById, executorAll));
            return serviceBuider;
        }
    }

    public static class DeleterBuilder<I, D, P, R>{
        private final ServiceBuider<I, D, P, R> serviceBuider;

        private DeletingAllExecutor<D> executorAll;
        private DeletingByIdExecutor<I, D> executorById;

        public DeleterBuilder(ServiceBuider<I, D, P, R> serviceBuider) {
            this.serviceBuider = serviceBuider;
        }

        public DeleterBuilder<I,D,P,R> executorAll(DeletingAllExecutor<D> executor){
            executorAll = executor;
            return this;
        }

        public DeleterBuilder<I,D,P,R> executorById(DeletingByIdExecutor<I, D> executor){
            executorById = executor;
            return this;
        }

        public ServiceBuider<I, D, P, R> build(){
            serviceBuider.deleter(new SimpleDeletingService<>(executorById, executorAll));
            return serviceBuider;
        }
    }

    public static class PredicateExecutorBuidler<I, D, P, R>{
        private final ServiceBuider<I, D, P, R> serviceBuider;

        private PredicateExecutor<P, D> executor;

        public PredicateExecutorBuidler(ServiceBuider<I, D, P, R> serviceBuider) {
            this.serviceBuider = serviceBuider;
        }

        public PredicateExecutorBuidler<I, D, P, R> executor(PredicateExecutor<P, D> executor){
            this.executor = executor;
            return this;
        }

        // TODO: rename with and()
        public ServiceBuider<I, D, P, R> build(){
            serviceBuider.predicateExecutor(new SimplePredicateService<>(executor));
            return serviceBuider;
        }
    }
}
