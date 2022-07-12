package kpn.lib.service;

import kpn.lib.aspect.deleting.DeletingAspect;
import kpn.lib.aspect.loading.LoadingAspect;
import kpn.lib.aspect.predicate.PredicateAspect;
import kpn.lib.aspect.saving.SavingAspect;

public interface Service<I, D, P, R> {
    SavingAspect<D, R> saver();
    LoadingAspect<I, R> loader();
    DeletingAspect<I, R> deleter();
    PredicateAspect<P, R> executor();
}