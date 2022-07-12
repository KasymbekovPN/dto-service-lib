package kpn.lib.aspect.predicate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.code.ErrorCode;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.DefaultExecutorResult;
import kpn.lib.executor.predicate.PredicateExecutor;
import kpn.utils.TestDomain;
import kpn.utils.TestPredicate;

class DefaultPredicateAspectTest {

    @Test
    void shouldCheckFailExecution(){
        AspectResult<TestDomain> result
            = new DefaultPredicateAspect<TestPredicate, TestDomain>(null).execute(new TestPredicate());
        
        assertThat(result).isEqualTo(new DefaultAspectResult<>(ErrorCode.EXECUTOR_PREDICATE_UNSUPPORTED.getValue()));
    }

    @Test
    void shouldCheckExecution() throws DTOException{
        AspectResult<TestDomain> result
            = new DefaultPredicateAspect<TestPredicate, TestDomain>(createExecutor()).execute(new TestPredicate());
    
        assertThat(result).isEqualTo(new DefaultAspectResult<>(new DefaultExecutorResult<>()));
    }

    private TestPredicateExecutor createExecutor() throws DTOException{
        TestPredicateExecutor executor = Mockito.mock(TestPredicateExecutor.class);
        Mockito
            .when(executor.execute(Mockito.anyObject()))
            .thenReturn(new DefaultExecutorResult<TestDomain>());
        return executor;
    }

    private abstract class TestPredicateExecutor implements PredicateExecutor<TestPredicate, TestDomain>{}
}
