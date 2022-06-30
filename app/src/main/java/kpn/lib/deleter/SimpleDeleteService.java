package kpn.lib.deleter;

import kpn.lib.collection.Collection;
import kpn.lib.converter.EDConverter;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.Domain;
import kpn.lib.entities.Entity;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleDeleteService<I, E extends Entity<I>, D extends Domain<I>, R> implements DeleteService<I, R>{
    private final DeleterByIdOld<I, E> deleterById;
    private final DeleterAllOld<I, E> deleterAll;
    private final EDConverter<I, D, E> edConverter;
    private final MultiConverter<Collection<D>, R> toResultConverter;

    public SimpleDeleteService(DeleterByIdOld<I, E> deleterById,
                               DeleterAllOld<I, E> deleterAll,
                               EDConverter<I, D, E> edConverter,
                               MultiConverter<Collection<D>, R> toResultConverter) {
        this.deleterById = deleterById;
        this.deleterAll = deleterAll;
        this.edConverter = edConverter;
        this.toResultConverter = toResultConverter;
    }

    @Override
    public R byId(I id) {
        try {
            return toResultConverter.convertValue(
                edConverter.toDomains(
                    deleterById.delete(id)        
                )
            );
        } catch (DTOServiceException e) {
            return toResultConverter.convertException(e);
        }
    }

    @Override
    public R all() {
        try {
            return toResultConverter.convertValue(
                edConverter.toDomains(
                    deleterAll.delete()       
                )
            );
        } catch (DTOServiceException e) {
            return toResultConverter.convertException(e);
        }
    }
}
