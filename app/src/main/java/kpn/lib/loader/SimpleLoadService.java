package kpn.lib.loader;

import kpn.lib.collection.DomainCollection;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.Domain;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleLoadService<I, D extends Domain<I>, R> implements LoadService<I, R> {

    private final LoaderById<I, D> loaderById;
    private final LoaderAll<I, D> loaderAll;
    private final MultiConverter<DomainCollection<D>, R> converter;

    public SimpleLoadService(LoaderById<I, D> loaderById,
                             LoaderAll<I, D> loaderAll,
                             MultiConverter<DomainCollection<D>, R> converter) {
        this.loaderById = loaderById;
        this.loaderAll = loaderAll;
        this.converter = converter;
    }

    @Override
    public R byId(I id) {
        try{
            DomainCollection<D> collection = loaderById.load(id);
            return converter.convertValue(collection);
        } catch (DTOServiceException e){
            return converter.convertException(e);
        }
    }

    @Override
    public R all() {
        try {
            DomainCollection<D> collection = loaderAll.load();
            return converter.convertValue(collection);
        } catch (DTOServiceException e) {
            return converter.convertException(e);
        }
    }
}
