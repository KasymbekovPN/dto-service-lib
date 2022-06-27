package kpn.utils;

import org.mockito.Mockito;

import kpn.lib.collection.Collection;
import kpn.lib.converter.MultiConverter;

public class MultiConvUtils {
    
    public static final String SUCCESS_RESULT = "success";
    public static final String FAIL_RESULT = "fail";

    public static MultiConverter<Collection<TestDomain>, String> create(){
        TestConverter converter = Mockito.mock(TestConverter.class);
        Mockito
            .when(converter.convertValue(Mockito.anyObject()))
            .thenReturn(SUCCESS_RESULT);
        Mockito
            .when(converter.convertException(Mockito.anyObject()))
            .thenReturn(FAIL_RESULT);
        return converter;
    }

    public abstract class TestConverter implements MultiConverter<Collection<TestDomain>, String>{}
}
