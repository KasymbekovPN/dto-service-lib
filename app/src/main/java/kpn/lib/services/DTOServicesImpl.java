package kpn.lib.services;

import kpn.lib.deleter.DeleteService;
import kpn.lib.domains.Domain;
import kpn.lib.loader.LoadService;
import kpn.lib.predicate.PredicateService;
import kpn.lib.services.saving.service.SaveService;

public class DTOServicesImpl<I, D extends Domain<I>, P, R> implements DTOServices<I, D, P, R>{

    @Override
    public SaveService<I, D, R> saver() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LoadService<I, R> loader() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DeleteService<I, R> deleter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PredicateService<P, R> executor() {
        // TODO Auto-generated method stub
        return null;
    }
}
