package kpn.lib.saver;

import java.util.function.Function;

import kpn.lib.exceptions.DTOServiceException;

public class SimpleSaveService<E, R> implements SaveService<E, R> {
    private final Saver<E> saver;
    private final Function<E, R> successConverter;
    private final Function<DTOServiceException, R> failConverter;

    public SimpleSaveService(Saver<E> saver,
                             Function<E, R> successConverter,
                             Function<DTOServiceException, R> failConverter) {
        this.saver = saver;
        this.successConverter = successConverter;
        this.failConverter = failConverter;
    }

    @Override
    public R save(E entity) {
        try{
            E saved = saver.save(entity);
            return successConverter.apply(saved);
        } catch(DTOServiceException ex){
            return failConverter.apply(ex);
        }
    }
}
