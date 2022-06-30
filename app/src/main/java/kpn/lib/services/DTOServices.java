package kpn.lib.services;

import kpn.lib.deleter.DeleteService;
import kpn.lib.domains.Domain;
import kpn.lib.loader.LoadService;
import kpn.lib.predicate.PredicateService;
import kpn.lib.services.parts.saving.service.SavingService;

public interface DTOServices<I, D, P, R> {
    SavingService<D, R> saver(D domain);


    // TODO: !!!
    // SaveService<I, D, R> saver();
    // LoadService<I, R> loader();
    // DeleteService<I, R> deleter();
    // PredicateService<P, R> executor();
}
