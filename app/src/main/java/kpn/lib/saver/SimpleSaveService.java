package kpn.lib.saver;

import kpn.lib.collection.Collection;
import kpn.lib.converter.EDConverter;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.Domain;
import kpn.lib.entities.Entity;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleSaveService<I, D extends Domain<I>, E extends Entity<I>, R> implements SaveService<I, D, R> {
    private final Saver<I, E> saver;
    private final EDConverter<I, D, E> edConverter;
    private final MultiConverter<Collection<D>, R> toResultConverter;

    public SimpleSaveService(Saver<I, E> saver,
                             EDConverter<I, D, E> edConverter,
                             MultiConverter<Collection<D>, R> toResultConverter) {
        this.saver = saver;
        this.edConverter = edConverter;
        this.toResultConverter = toResultConverter;
    }

    @Override
    public R save(D domain) {
        E entity = edConverter.toEntity(domain);
        try {
            return toResultConverter.convertValue(
                edConverter.toDomains(
                    saver.save(entity)
                )
            );
        } catch (DTOServiceException e) {
            return toResultConverter.convertException(e);
        }
    }
}
