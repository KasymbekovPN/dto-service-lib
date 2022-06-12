package kpn.lib.saver;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.exceptions.DTOServiceException;

public class SimpleSaverServiceTest {
    
    @Test
    public void shouldCheckFailSaving(){
        String expectedResult = "wrong 123";
        int entity = 123;
        SimpleSaveService<Integer, String> service 
            = new SimpleSaveService<>(new FailTestSaver(), null, createFailConverter(expectedResult));

        String result = service.save(entity);
        assertThat(expectedResult).isEqualTo(result);
    }

    @Test
    public void shouldCheckSuccessSaving(){
        String expectedResult = "success 123";
        int entity = 123;
        SimpleSaveService<Integer, String> service 
            = new SimpleSaveService<>(new SuccessTestSaver(), createSuccessConverter(expectedResult, entity), null);

        String result = service.save(entity);
        assertThat(expectedResult).isEqualTo(result);
    }

    private Function<DTOServiceException, String> createFailConverter(String expectedResult) {
        TestFailConverter converter = Mockito.mock(TestFailConverter.class);
        Mockito
            .when(converter.apply(Mockito.anyObject()))
            .thenReturn(expectedResult);
        return converter;
    }

    private Function<Integer, String> createSuccessConverter(String expectedResult, int entity) {
        TestSuccessConverter converter = Mockito.mock(TestSuccessConverter.class);
        Mockito
            .when(converter.apply(entity))
            .thenReturn(expectedResult);
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

    private abstract class TestFailConverter implements Function<DTOServiceException, String> {}
    private abstract class TestSuccessConverter implements Function<Integer, String> {}
}
