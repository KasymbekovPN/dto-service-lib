package kpn.lib.services;

import kpn.lib.deleter.DeleteServiceOld;
import kpn.lib.domains.Domain;
import kpn.lib.loader.LoadServiceOld;
import kpn.lib.predicate.PredicateServiceOld;
import kpn.lib.services.parts.saving.service.SavingService;

public interface DTOServices<I, D, P, R> {
    SavingService<D, R> saver();


    // TODO: !!!
    // SaveService<I, D, R> saver();
    // LoadService<I, R> loader();
    // DeleteService<I, R> deleter();
    // PredicateService<P, R> executor();
}
