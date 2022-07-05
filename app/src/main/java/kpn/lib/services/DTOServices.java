package kpn.lib.services;

import kpn.lib.services.parts.deleting.service.DeletingService;
import kpn.lib.services.parts.loading.service.LoadingService;
import kpn.lib.services.parts.predicate.service.PredicateService;
import kpn.lib.services.parts.saving.service.SavingService;

public interface DTOServices<I, D, P, R> {
    SavingService<D, R> saver();
    LoadingService<I, R> loader();
    DeletingService<I, R> deleter();
    PredicateService<P, R> executor();
}