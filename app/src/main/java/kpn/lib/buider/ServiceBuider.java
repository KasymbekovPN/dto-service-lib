package kpn.lib.buider;

import java.util.function.Function;

import kpn.lib.executor.deleting.CompletelyDeletingExecutor;
import kpn.lib.executor.loading.CompletelyLoadingExecutor;
import kpn.lib.executor.predicate.PredicateExecutor;
import kpn.lib.executor.saving.SavingExecutor;
import kpn.lib.service.Service;
import kpn.lib.service.DtoService;
import kpn.lib.executor.loading.ByIdLoadingExecutor;
import kpn.lib.aspect.deleting.DeletingAspect;
import kpn.lib.aspect.loading.LoadingAspect;
import kpn.lib.aspect.predicate.PredicateAspect;
import kpn.lib.aspect.saving.SavingAspect;
import kpn.lib.aspect.saving.DefaultSavingAspect;
import kpn.lib.aspect.predicate.DefaultPredicateAspect;
import kpn.lib.aspect.loading.DefaultLoadingAspect;
import kpn.lib.aspect.AspectResult;
import kpn.lib.aspect.deleting.DefaultDeletingAspect;
import kpn.lib.domain.Domain;
import kpn.lib.executor.deleting.ByIdDeletingExecutor;

public final class ServiceBuider<I, D extends Domain<?>, P, R> {

    private final Function<AspectResult<D>, R> converter;

    private SavingAspect<D, AspectResult<D>> savingAspect;
    private LoadingAspect<I, AspectResult<D>> loadingAspect;
    private DeletingAspect<I, AspectResult<D>> deletingAspect;
    private PredicateAspect<P, AspectResult<D>> predicateAspect;

    public ServiceBuider(Function<AspectResult<D>, R> converter) {
        this.converter = converter;
    }

    public Service<I,D,P,R> build(){
        return new DtoService<>(savingAspect, loadingAspect, deletingAspect, predicateAspect, converter);
    }

    public ServiceBuider<I,D,P,R> savingAspect(SavingAspect<D, AspectResult<D>> savingAspect){
        this.savingAspect = savingAspect;
        return this;
    }

    public SavingAspectBuidler<I,D,P,R> savingAspectBuider(){
        return new SavingAspectBuidler<>(this);
    }

    public ServiceBuider<I,D,P,R> loadingAspect(LoadingAspect<I, AspectResult<D>> loadingAspect){
        this.loadingAspect = loadingAspect;
        return this;
    }

    public LoadingAspectBuilder<I,D,P,R> loadingAspectBuilder(){
        return new LoadingAspectBuilder<>(this);
    }

    public ServiceBuider<I,D,P,R> deletingAspect(DeletingAspect<I, AspectResult<D>> deletingAspect){
        this.deletingAspect = deletingAspect;
        return this;
    }

    public DeletingAspectBuilder<I,D,P,R> deletingAspectBuilder(){
        return new DeletingAspectBuilder<>(this);
    }

    public ServiceBuider<I,D,P,R> predicateAspect(PredicateAspect<P, AspectResult<D>> predicateAspect){
        this.predicateAspect = predicateAspect;
        return this;
    }

    public PredicateAspectBuidler<I,D,P,R> predicateAspectBuidler(){
        return new PredicateAspectBuidler<>(this);
    }

    public static class SavingAspectBuidler<I, D extends Domain<?>, P, R>{
        private final ServiceBuider<I, D, P, R> serviceBuider;

        private SavingExecutor<D> executor;

        public SavingAspectBuidler(ServiceBuider<I, D, P, R> serviceBuider) {
            this.serviceBuider = serviceBuider;
        }

        public SavingAspectBuidler<I, D, P, R> executor(SavingExecutor<D> executor){
            this.executor = executor;
            return this;
        }

        public ServiceBuider<I, D, P, R> and(){
            serviceBuider.savingAspect(new DefaultSavingAspect<>(executor));
            return serviceBuider;
        }
    }

    public static class LoadingAspectBuilder<I, D extends Domain<?>, P, R>{
        private final ServiceBuider<I, D, P, R> serviceBuider;

        private CompletelyLoadingExecutor<D> completelyLoadingExecutor;
        private ByIdLoadingExecutor<I, D> byIdLoadingExecutor;

        public LoadingAspectBuilder(ServiceBuider<I, D, P, R> serviceBuider) {
            this.serviceBuider = serviceBuider;
        }

        public LoadingAspectBuilder<I,D,P,R> executorAll(CompletelyLoadingExecutor<D> executor){
            completelyLoadingExecutor = executor;
            return this;
        }

        public LoadingAspectBuilder<I,D,P,R> executorById(ByIdLoadingExecutor<I, D> executor){
            byIdLoadingExecutor = executor;
            return this;
        }

        public ServiceBuider<I, D, P, R> and(){
            serviceBuider.loadingAspect(new DefaultLoadingAspect<>(byIdLoadingExecutor, completelyLoadingExecutor));
            return serviceBuider;
        }
    }

    public static class DeletingAspectBuilder<I, D extends Domain<?>, P, R>{
        private final ServiceBuider<I, D, P, R> serviceBuider;

        private CompletelyDeletingExecutor<D> completelyDeletingExecutor;
        private ByIdDeletingExecutor<I, D> byIdDeletingExecutor;

        public DeletingAspectBuilder(ServiceBuider<I, D, P, R> serviceBuider) {
            this.serviceBuider = serviceBuider;
        }

        public DeletingAspectBuilder<I,D,P,R> executorAll(CompletelyDeletingExecutor<D> executor){
            completelyDeletingExecutor = executor;
            return this;
        }

        public DeletingAspectBuilder<I,D,P,R> executorById(ByIdDeletingExecutor<I, D> executor){
            byIdDeletingExecutor = executor;
            return this;
        }

        public ServiceBuider<I, D, P, R> and(){
            serviceBuider.deletingAspect(new DefaultDeletingAspect<>(byIdDeletingExecutor, completelyDeletingExecutor));
            return serviceBuider;
        }
    }

    public static class PredicateAspectBuidler<I, D extends Domain<?>, P, R>{
        private final ServiceBuider<I, D, P, R> serviceBuider;

        private PredicateExecutor<P, D> executor;

        public PredicateAspectBuidler(ServiceBuider<I, D, P, R> serviceBuider) {
            this.serviceBuider = serviceBuider;
        }

        public PredicateAspectBuidler<I, D, P, R> executor(PredicateExecutor<P, D> executor){
            this.executor = executor;
            return this;
        }

        public ServiceBuider<I, D, P, R> and(){
            serviceBuider.predicateAspect(new DefaultPredicateAspect<>(executor));
            return serviceBuider;
        }
    }
}
