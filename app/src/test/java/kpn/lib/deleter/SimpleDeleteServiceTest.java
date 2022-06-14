package kpn.lib.deleter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.converter.MultiConverter;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleDeleteServiceTest {
    
    private static final String EXPECTED_RESULT = "expected.result";

    @Test
    public void shouldCheckFailByIdDeleting(){
        SimpleDeleteService<String, Integer> service = new SimpleDeleteService<>(
            new TestFailDeleterById(), 
            null,
            createConverter(null, EXPECTED_RESULT)
        );

        String result = service.byId(1);
        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }

    @Test
    public void shouldCheckByIdDeleting(){
        SimpleDeleteService<String, Integer> service = new SimpleDeleteService<>(
            new TestDeleterById(), 
            null,
            createConverter(EXPECTED_RESULT, null)
        );

        String result = service.byId(1);
        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }

    @Test
    public void shouldCheckFailFullDeleting(){
        SimpleDeleteService<String, Integer> service = new SimpleDeleteService<>(
            null, 
            new TestFailDeleterAll(),
            createConverter(null, EXPECTED_RESULT)
        );

        String result = service.all();
        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }

    @Test
    public void shouldCheckFullDeleting(){
        SimpleDeleteService<String, Integer> service = new SimpleDeleteService<>(
            null, 
            new TestDeleterAll(),
            createConverter(EXPECTED_RESULT, null)
        );

        String result = service.all();
        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }

    private MultiConverter<Void, String> createConverter(String successExpectedResult, String failExpectedResult) {
        TestConverter converter = Mockito.mock(TestConverter.class);
        Mockito
            .when(converter.convertValue(Mockito.anyObject()))
            .thenReturn(successExpectedResult);
        Mockito
            .when(converter.convertException(Mockito.anyObject()))
            .thenReturn(failExpectedResult);
        return converter;
    }

    private class TestFailDeleterById implements DeleterById<Integer> {
        @Override
        public void delete(Integer id) throws DTOServiceException {
            throw new DTOServiceException("");
        }
    }

    private class TestDeleterById implements DeleterById<Integer>{
        @Override
        public void delete(Integer id) throws DTOServiceException {}
    }

    private class TestFailDeleterAll implements DeleterAll {
        @Override
        public void delete() throws DTOServiceException {
            throw new DTOServiceException("");
        }
    }

    private class TestDeleterAll implements DeleterAll {
        @Override
        public void delete() throws DTOServiceException {}
    }

    private abstract class TestConverter implements MultiConverter<Void, String> {}
}
