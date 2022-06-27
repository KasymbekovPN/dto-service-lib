package kpn.lib.loader;

import kpn.lib.collection.Collection;
import kpn.lib.converter.EDConverter;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.Domain;
import kpn.lib.entities.Entity;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleLoadService<I, E extends Entity<I>, D extends Domain<I>, R> implements LoadService<I, R> {
    private final LoaderById<I, E> loaderById;
    private final LoaderAll<I, E> loaderAll;
    private final EDConverter<I, D, E> edConverter;
    private final MultiConverter<Collection<D>, R> toResultConverter;

    public SimpleLoadService(LoaderById<I, E> loaderById,
                             LoaderAll<I, E> loaderAll,
                             EDConverter<I, D, E> edConverter,
                             MultiConverter<Collection<D>, R> toResultConverter) {
        this.loaderById = loaderById;
        this.loaderAll = loaderAll;
        this.edConverter = edConverter;
        this.toResultConverter = toResultConverter;
    }

    @Override
    public R byId(I id) {
        try {
            return toResultConverter.convertValue(
                edConverter.toDomains(
                    loaderById.load(id)
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
                    loaderAll.load()       
                )
            );
        } catch (DTOServiceException e) {
            return toResultConverter.convertException(e);
        }
    }
}
