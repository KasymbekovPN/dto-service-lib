package kpn.lib.deleter;

import kpn.lib.collection.DomainCollection;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.Domain;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleDeleteService<I, D extends Domain<I>, R> implements DeleteService<I, R>{
    private final DeleterById<I, D> deleterById;
    private final DeleterAll<I, D> deleterAll;
    private final MultiConverter<DomainCollection<D>, R> converter;

    public SimpleDeleteService(DeleterById<I, D> deleterById,
                               DeleterAll<I, D> deleterAll,
                               MultiConverter<DomainCollection<D>, R> converter) {
        this.deleterById = deleterById;
        this.deleterAll = deleterAll;
        this.converter = converter;
    }

    @Override
    public R byId(I id) {
        try {
            DomainCollection<D> collection = deleterById.delete(id);
            return converter.convertValue(collection);
        } catch (DTOServiceException e) {
            return converter.convertException(e);
        }
    }

    @Override
    public R all() {
        try {
            DomainCollection<D> collection = deleterAll.delete();
            return converter.convertValue(collection);
        } catch (DTOServiceException e) {
            return converter.convertException(e);
        }
    }
}
