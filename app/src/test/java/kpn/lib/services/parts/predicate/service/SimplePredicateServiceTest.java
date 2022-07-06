package kpn.lib.services.parts.predicate.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.code.Code;
import kpn.lib.collection.ImmutableCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.lib.services.parts.predicate.executor.PredicateExecutor;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;
import kpn.utils.TestDomain;
import kpn.utils.TestPredicate;

class SimplePredicateServiceTest {

    @Test
    void shouldCheckFailExecution(){
        ServiceResult<TestDomain> result
            = new SimplePredicateService<TestPredicate, TestDomain>(null).execute(new TestPredicate());
        
        assertThat(result).isEqualTo(new ImmutableServiceResult<>(Code.EXECUTOR_PREDICATE_UNSUPPORTED.getValue()));
    }

    @Test
    void shouldCheckExecution() throws DTOServiceException{
        ServiceResult<TestDomain> result
            = new SimplePredicateService<TestPredicate, TestDomain>(createExecutor()).execute(new TestPredicate());
    
        assertThat(result).isEqualTo(new ImmutableServiceResult<>(new ImmutableCollection<>()));
    }

    private TestPredicateExecutor createExecutor() throws DTOServiceException{
        TestPredicateExecutor executor = Mockito.mock(TestPredicateExecutor.class);
        Mockito
            .when(executor.execute(Mockito.anyObject()))
            .thenReturn(new ImmutableCollection<TestDomain>());
        return executor;
    }

    private abstract class TestPredicateExecutor implements PredicateExecutor<TestPredicate, TestDomain>{}
}
