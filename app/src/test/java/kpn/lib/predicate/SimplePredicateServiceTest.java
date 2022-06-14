package kpn.lib.predicate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.converter.MultiConverter;
import kpn.lib.exceptions.DTOServiceException;

public class SimplePredicateServiceTest {
    
    private static final String EXPECTED_RESULT = "expected.result";

    @Test
    public void shouldCheckFailExecution(){
        SimplePredicateService<String, Float, Integer> executor = new SimplePredicateService<>(
            new TestFailExecutor(),
            createConverter(null, EXPECTED_RESULT)
        );

        String result = executor.execute(1.0f);
        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }

    @Test
    public void shouldCheckExecution(){
        SimplePredicateService<String, Float, Integer> executor = new SimplePredicateService<>(
            new TestExecutor(),
            createConverter(EXPECTED_RESULT, null)
        );

        String result = executor.execute(1.0f);
        assertThat(result).isEqualTo(EXPECTED_RESULT);
    }

    private MultiConverter<List<Integer>, String> createConverter(String successExpectedResult, String failExpectedResult) {
        TestConverter converter = Mockito.mock(TestConverter.class);
        Mockito
            .when(converter.convertValue(Mockito.anyObject()))
            .thenReturn(successExpectedResult);
        Mockito
            .when(converter.convertException(Mockito.anyObject()))
            .thenReturn(failExpectedResult);
        return converter;
    }

    private class TestFailExecutor implements PredicateExecutor<Float, Integer>{
        @Override
        public List<Integer> execute(Float predicate) throws DTOServiceException {
            throw new DTOServiceException("");
        }
    }

    private class TestExecutor implements PredicateExecutor<Float, Integer>{
        @Override
        public List<Integer> execute(Float predicate) throws DTOServiceException {
            return List.of();
        }
    }

    private abstract class TestConverter implements MultiConverter<List<Integer>, String>{}
}
