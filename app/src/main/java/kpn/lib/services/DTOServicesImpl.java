package kpn.lib.services;

import java.util.function.Function;

import kpn.lib.deleter.DeleteService;
import kpn.lib.domains.Domain;
import kpn.lib.loader.LoadService;
import kpn.lib.predicate.PredicateService;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;
import kpn.lib.services.saving.service.SavingService;

public class DTOServicesImpl<I, D, P, R> implements DTOServices<I, D, P, R> {
    private final SavingService<D, ServiceResult<D>> saver;
    private final Function<ServiceResult<D>, R> converter;

    public DTOServicesImpl(SavingService<D, ServiceResult<D>> saver, Function<ServiceResult<D>, R> converter) {
        this.saver = saver;
        this.converter = converter;
    }

    @Override
    public SavingService<D, R> saver(D domain) {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO: private ???
    public static class SavingDecorator<D, R> implements SavingService<D, R>{
        private final SavingService<D, ServiceResult<D>> saver;
        private final Function<ServiceResult<D>, R> converter;

        public SavingDecorator(SavingService<D, ServiceResult<D>> saver,
                               Function<ServiceResult<D>, R> converter) {
            this.saver = saver;
            this.converter = converter;
        }

        @Override
        public R save(D domain) {
            // TODO Auto-generated method stub
            return null;
        }
    }

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
