package kpn.lib.loader;

import java.util.List;

import kpn.lib.converter.MultiConverter;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleLoadService<R, L, I, E> implements LoadService<R, L, I> {
    private final LoaderById<E, I> loaderById;
    private final LoaderAll<E> loaderAll;
    private final MultiConverter<E, R> converterById;
    private final MultiConverter<List<E>, L> converterAll;

    public SimpleLoadService(LoaderById<E, I> loaderById, 
                             LoaderAll<E> loaderAll,
                             MultiConverter<E, R> converterById,
                             MultiConverter<List<E>, L> converterAll) {
        this.loaderById = loaderById;
        this.loaderAll = loaderAll;
        this.converterById = converterById;
        this.converterAll = converterAll;
    }

    @Override
    public R byId(I id) {
        try{
            E loaded = loaderById.load(id);
            return converterById.convertValue(loaded);
        } catch(DTOServiceException ex){
            return converterById.convertException(ex);
        }
    }

    @Override
    public L all() {
        try{
            List<E> loaded = loaderAll.load();
            return converterAll.convertValue(loaded);
        } catch(DTOServiceException ex){
            return converterAll.convertException(ex);
        }
    }
}
