package kpn.lib.saver;

import kpn.lib.converter.MultiConverter;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleSaveService<E, R> implements SaveService<E, R> {
    private final Saver<E> saver;
    private final MultiConverter<E, R> converter;

    public SimpleSaveService(Saver<E> saver,
                             MultiConverter<E, R> converter) {
        this.saver = saver;
        this.converter = converter;
    }

    @Override
    public R save(E entity) {
        try{
            E saved = saver.save(entity);
            return converter.convertValue(saved);
        } catch(DTOServiceException ex){
            return converter.convertException(ex);
        }
    }
}
