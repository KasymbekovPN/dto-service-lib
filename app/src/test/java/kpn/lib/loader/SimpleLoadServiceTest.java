package kpn.lib.loader;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.converter.MultiConverter;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleLoadServiceTest {
    
    @Test
    public void shouldCheckFailLoadingById(){
        String expectedResult = "wrong 1";

        SimpleLoadService<String, List<String>, Integer, Integer> service = new SimpleLoadService<>(
            new TestFailLoaderById(),
            null,
            createConverterById(null, expectedResult),
            null
        );

        String result = service.byId(1);
        assertThat(expectedResult).isEqualTo(result);
    }

    @Test
    public void shouldCheckLoadingById() throws DTOServiceException{
        String expectedResult = "success 1";
        Integer id = 1;

        SimpleLoadService<String, List<String>, Integer, Integer> service = new SimpleLoadService<>(
            createSuccessLoaderById(id),
            null,
            createConverterById(expectedResult, null),
            null
        );

        String result = service.byId(id);
        assertThat(expectedResult).isEqualTo(result);
    }

    @Test
    public void shouldCheckFailFullLoading(){
        List<String> expectedResult = List.of("wrong 1");

        SimpleLoadService<String, List<String>, Integer, Integer> service = new SimpleLoadService<>(
            null,
            new TestFailLoaderAll(),
            null,
            createConverterAll(null, expectedResult)
        );

        List<String> result = service.all();
        assertThat(expectedResult).isEqualTo(result);
    }

    @Test
    public void shouldCheckFullLoading() throws DTOServiceException{
        List<String> expectedResult = List.of("success 1");

        SimpleLoadService<String, List<String>, Integer, Integer> service = new SimpleLoadService<>(
            null,
            createSuccessLoaderAll(List.of(1)),
            null,
            createConverterAll(expectedResult, null)
        );

        List<String> result = service.all();
        assertThat(expectedResult).isEqualTo(result);
    }

    private MultiConverter<Integer, String> createConverterById(String successExpectedResult, String failExpectedResult) {
        TestConverter converter = Mockito.mock(TestConverter.class);
        Mockito
            .when(converter.convertValue(Mockito.anyObject()))
            .thenReturn(successExpectedResult);
        Mockito
            .when(converter.convertException(Mockito.anyObject()))
            .thenReturn(failExpectedResult);
        return converter;
    }

    private LoaderById<Integer, Integer> createSuccessLoaderById(Integer id) throws DTOServiceException {
        TestLoaderById loader = Mockito.mock(TestLoaderById.class);
        Mockito
            .when(loader.load(id))
            .thenReturn(id);
        return loader;
    }

    private MultiConverter<List<Integer>, List<String>> createConverterAll(List<String> successExpectedResult, List<String> failExpectedResult) {
        TestListConverter converter = Mockito.mock(TestListConverter.class);
        Mockito
            .when(converter.convertValue(Mockito.anyObject()))
            .thenReturn(successExpectedResult);
        Mockito
            .when(converter.convertException(Mockito.anyObject()))
            .thenReturn(failExpectedResult);
        return converter;
    }

    private LoaderAll<Integer> createSuccessLoaderAll(List<Integer> expectedResult) throws DTOServiceException {
        TestLoaderAll loader = Mockito.mock(TestLoaderAll.class);
        Mockito
            .when(loader.load())
            .thenReturn(expectedResult);
        return loader;
    }

    private class TestFailLoaderById implements LoaderById<Integer, Integer>{
        @Override
        public Integer load(Integer id) throws DTOServiceException {
            throw new DTOServiceException("");
        }
    }

    private class TestFailLoaderAll implements LoaderAll<Integer>{

        @Override
        public List<Integer> load() throws DTOServiceException {
            throw new DTOServiceException("");
        }

    }

    private abstract class TestLoaderById implements LoaderById<Integer, Integer>{}
    private abstract class TestLoaderAll implements LoaderAll<Integer>{}
    private abstract class TestConverter implements MultiConverter<Integer, String> {}
    private abstract class TestListConverter implements MultiConverter<List<Integer>, List<String>> {}
}
