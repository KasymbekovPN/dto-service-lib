package kpn.lib.service;

import java.util.function.Function;

import kpn.lib.aspect.AspectResult;
import kpn.lib.aspect.deleting.DeletingAspect;
import kpn.lib.aspect.deleting.DeletingAspectDecorator;
import kpn.lib.aspect.loading.LoadingAspect;
import kpn.lib.aspect.loading.LoadingAspectDecorator;
import kpn.lib.aspect.predicate.PredicateAspect;
import kpn.lib.aspect.predicate.PredicateAspectDecorator;
import kpn.lib.aspect.saving.SavingAspect;
import kpn.lib.aspect.saving.SavingAspectDecorator;
import kpn.lib.domain.Domain;

public class DtoService<I, D extends Domain<?>, P, R> implements Service<I, D, P, R> {
    private final SavingAspect<D, AspectResult<D>> savingApect;
    private final LoadingAspect<I, AspectResult<D>> loadingAspect;
    private final DeletingAspect<I, AspectResult<D>> deletingAspect;
    private final PredicateAspect<P, AspectResult<D>> predicateAspect;
    private final Function<AspectResult<D>, R> converter;

    public DtoService(SavingAspect<D, AspectResult<D>> savingApect, 
                      LoadingAspect<I, AspectResult<D>> loadingAspect,
                      DeletingAspect<I, AspectResult<D>> deletingAspect,
                      PredicateAspect<P, AspectResult<D>> predicateAspect,
                      Function<AspectResult<D>, R> converter) {
        this.savingApect = savingApect;
        this.loadingAspect = loadingAspect;
        this.deletingAspect = deletingAspect;
        this.predicateAspect = predicateAspect;
        this.converter = converter;
    }

    @Override
    public SavingAspect<D, R> saver() {
        return new SavingAspectDecorator<>(savingApect, converter);
    }

    @Override
    public LoadingAspect<I, R> loader() {
        return new LoadingAspectDecorator<>(loadingAspect, converter);
    }

    @Override
    public DeletingAspect<I, R> deleter() {
        return new DeletingAspectDecorator<>(deletingAspect, converter);
    }

    @Override
    public PredicateAspect<P, R> executor() {
        return new PredicateAspectDecorator<>(predicateAspect, converter);
    }
}
