package kpn.lib.saver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.converter.MultiConverter;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleSaverServiceTest {
    
    @Test
    public void shouldCheckFailSaving(){
        String expectedResult = "wrong 123";
        int entity = 123;

        SimpleSaveService<Integer, String> service 
            = new SimpleSaveService<>(new FailTestSaver(), createConverter(null, expectedResult));

        String result = service.save(entity);
        assertThat(expectedResult).isEqualTo(result);
    }

    @Test
    public void shouldCheckSuccessSaving(){
        String expectedResult = "success 123";
        int entity = 123;
        SimpleSaveService<Integer, String> service 
            = new SimpleSaveService<>(new SuccessTestSaver(), createConverter(expectedResult, null));

        String result = service.save(entity);
        assertThat(expectedResult).isEqualTo(result);
    }

    private MultiConverter<Integer, String> createConverter(String successExpectedResult, String failExpectedResult) {
        TestMultiConverter converter = Mockito.mock(TestMultiConverter.class);
        Mockito
            .when(converter.convertValue(Mockito.anyObject()))
            .thenReturn(successExpectedResult);
        Mockito
            .when(converter.convertException(Mockito.anyObject()))
            .thenReturn(failExpectedResult);
        return converter;
    }

    private class FailTestSaver implements Saver<Integer> {

        @Override
        public Integer save(Integer entity) throws DTOServiceException {
            throw new DTOServiceException("");
        }
    }

    private class SuccessTestSaver implements Saver<Integer>{

        @Override
        public Integer save(Integer entity) throws DTOServiceException {
            return entity;
        }

    }

    private abstract class TestMultiConverter implements MultiConverter<Integer, String>{}
}
