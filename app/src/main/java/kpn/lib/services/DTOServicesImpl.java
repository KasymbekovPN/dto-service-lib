package kpn.lib.services;

import java.util.function.Function;

import kpn.lib.deleter.DeleteServiceOld;
import kpn.lib.domains.Domain;
import kpn.lib.loader.LoadServiceOld;
import kpn.lib.predicate.PredicateServiceOld;
import kpn.lib.services.parts.saving.service.SavingService;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;

public class DTOServicesImpl<I, D, P, R> implements DTOServices<I, D, P, R> {
    private final SavingService<D, ServiceResult<D>> saver;
    private final Function<ServiceResult<D>, R> converter;

    public DTOServicesImpl(SavingService<D, ServiceResult<D>> saver, Function<ServiceResult<D>, R> converter) {
        this.saver = saver;
        this.converter = converter;
    }

    @Override
    public SavingService<D, R> saver() {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO: del
    // TODO: private ???
    // public static class SavingDecorator<D, R> implements SavingService<D, R>{
    //     private final SavingService<D, ServiceResult<D>> saver;
    //     private final Function<ServiceResult<D>, R> converter;

    //     public SavingDecorator(SavingService<D, ServiceResult<D>> saver,
    //                            Function<ServiceResult<D>, R> converter) {
    //         this.saver = saver;
    //         this.converter = converter;
    //     }

    //     @Override
    //     public R save(D domain) {
    //         return null;
    //     }
    // }

    // private static class SaverDecorator<D> implements SavingService<D>{
    //     private final SavingService<D> saver;

    //     public SaverDecorator(SavingService<D> saver) {
    //         this.saver = saver;
    //     }

    //     // TODO: must ret R
    //     @Override
    //     public ServiceResult<D> save(D domain) {
    //         return saver != null
    //             ? saver.save(domain)
    //             : new ImmutableServiceResult<>("service.saving.absent");
    //     }
    // }
}
