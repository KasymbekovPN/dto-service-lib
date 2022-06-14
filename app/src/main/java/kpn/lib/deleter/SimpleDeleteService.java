package kpn.lib.deleter;

import kpn.lib.converter.MultiConverter;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleDeleteService<R, I> implements DeleteService<R, I>{
    private final DeleterById<I> deleterById;
    private final DeleterAll deleterAll;
    private final MultiConverter<Void, R> converter;

    public SimpleDeleteService(DeleterById<I> deleterById,
                               DeleterAll deleterAll,
                               MultiConverter<Void, R> converter) {
        this.deleterById = deleterById;
        this.deleterAll = deleterAll;
        this.converter = converter;
    }

    @Override
    public R byId(I id) {
        try {
            deleterById.delete(id);
            return converter.convertValue(null);
        } catch (DTOServiceException e) {
            return converter.convertException(e);
        }
    }

    @Override
    public R all() {
        try {
            deleterAll.delete();
            return converter.convertValue(null);
        } catch (DTOServiceException e) {
            return converter.convertException(e);
        }
    }
}
