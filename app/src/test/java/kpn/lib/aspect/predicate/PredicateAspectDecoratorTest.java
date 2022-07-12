package kpn.lib.aspect.predicate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.code.ErrorCode;
import kpn.lib.executor.DefaultExecutorResult;
import kpn.utils.TestDomain;
import kpn.utils.TestPredicate;
import kpn.utils.TestResult;
import kpn.utils.TestToResultConverter;

class PredicateAspectDecoratorTest {
    
    private final TestDomain domain = new TestDomain(1L);
    private final Function<AspectResult<TestDomain>, TestResult<TestDomain>> converter = new TestToResultConverter();
 
    @Test
    void shouldCheckExecution_ifServiceNull(){
        PredicateAspectDecorator<TestPredicate, TestDomain, TestResult<TestDomain>> decorator = new PredicateAspectDecorator<>(null, converter);
        TestResult<TestDomain> result = decorator.execute(new TestPredicate());

        TestResult<TestDomain> expectedResult = new TestResult<>(ErrorCode.SERVICE_PREDICATE_UNSUPPORTED.getValue());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckExecution(){
        PredicateAspectDecorator<TestPredicate, TestDomain, TestResult<TestDomain>> decorator = new PredicateAspectDecorator<>(createServive(), converter);
        TestResult<TestDomain> result = decorator.execute(new TestPredicate());

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(new DefaultExecutorResult<>(domain)));
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestPredicateService createServive(){
        TestPredicateService service = Mockito.mock(TestPredicateService.class);
        Mockito
            .when(service.execute(Mockito.anyObject()))
            .thenReturn(new DefaultAspectResult<>(new DefaultExecutorResult<TestDomain>(domain)));
        return service;
    }

    private abstract class TestPredicateService implements PredicateAspect<TestPredicate, AspectResult<TestDomain>>{}
}
