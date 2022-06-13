package kpn.lib.converter;

import kpn.lib.exceptions.DTOServiceException;

public interface MultiConverter<T, R> {
    R convertValue(T value);
    R convertException(DTOServiceException exception);
}
