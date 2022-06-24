package kpn.lib.predicate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.collection.DomainCollection;
import kpn.lib.collection.ImmutableDomainCollection;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.AbstractDomain;
import kpn.lib.exceptions.DTOServiceException;

public class SimplePredicateServiceTest {
    
    private static final String SUCCESS_RESULT = "success";
    private static final String FAIL_RESULT = "fail";

    @Test
    public void shouldCheckFailExecution(){
        SimplePredicateService<Long, TestDomain, Integer, String> service 
            = new SimplePredicateService<>(new TestPredicateExecutor(false), createConverter());

        String result = service.execute(1);
        assertThat(result).isEqualTo(FAIL_RESULT);
    }

    @Test
    public void shouldCheckExecution(){
        SimplePredicateService<Long, TestDomain, Integer, String> service 
            = new SimplePredicateService<>(new TestPredicateExecutor(true), createConverter());

        String result = service.execute(1);
        assertThat(result).isEqualTo(SUCCESS_RESULT);
    }

    // TODO: move
    private TestConverter createConverter(){
        TestConverter converter = Mockito.mock(TestConverter.class);
        Mockito
            .when(converter.convertValue(Mockito.anyObject()))
            .thenReturn(SUCCESS_RESULT);
        Mockito
            .when(converter.convertException(Mockito.anyObject()))
            .thenReturn(FAIL_RESULT);
        return converter;
    }

    // TODO: move
    private static class TestDomain extends AbstractDomain<Long>{
        public TestDomain(long id) {
            setId(id);
        }
    }

    private static class TestPredicateExecutor implements PredicateExecutor<Integer, Long, TestDomain>{
        private final boolean success;

        public TestPredicateExecutor(boolean success) {
            this.success = success;
        }

        @Override
        public DomainCollection<TestDomain> execute(Integer predicate) throws DTOServiceException {
            if (success){
                return new ImmutableDomainCollection<>(new TestDomain(0L));
            }
            throw new DTOServiceException("");
        }
    } 

    // TODO: move
    private abstract class TestConverter implements MultiConverter<DomainCollection<TestDomain>, String>{}

    // TODO: del
    // private MultiConverter<List<Integer>, String> createConverter(String successExpectedResult, String failExpectedResult) {
    //     TestConverter converter = Mockito.mock(TestConverter.class);
    //     Mockito
    //         .when(converter.convertValue(Mockito.anyObject()))
    //         .thenReturn(successExpectedResult);
    //     Mockito
    //         .when(converter.convertException(Mockito.anyObject()))
    //         .thenReturn(failExpectedResult);
    //     return converter;
    // }

    // private class TestFailExecutor implements PredicateExecutor<Float, Integer>{
    //     @Override
    //     public List<Integer> execute(Float predicate) throws DTOServiceException {
    //         throw new DTOServiceException("");
    //     }
    // }

    // private class TestExecutor implements PredicateExecutor<Float, Integer>{
    //     @Override
    //     public List<Integer> execute(Float predicate) throws DTOServiceException {
    //         return List.of();
    //     }
    // }

    // private abstract class TestConverter implements MultiConverter<List<Integer>, String>{}
}
