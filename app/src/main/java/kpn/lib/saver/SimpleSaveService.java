package kpn.lib.saver;

import kpn.lib.collection.DomainCollection;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.Domain;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleSaveService<I, D extends Domain<I>, R> implements SaveService<I, D, R> {
    
    private final Saver<I, D> saver;
    private final MultiConverter<DomainCollection<D>, R> converter;

    public SimpleSaveService(Saver<I, D> saver,
                             MultiConverter<DomainCollection<D>, R> converter) {
        this.saver = saver;
        this.converter = converter;
    }

    @Override
    public R save(D domain) {
        try {
            DomainCollection<D> collection = saver.save(domain);
            return converter.convertValue(collection);
        } catch (DTOServiceException e) {
            return converter.convertException(e);
        }
    }
}
