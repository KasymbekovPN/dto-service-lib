package kpn.lib.services.parts.predicate.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.code.Code;
import kpn.lib.collection.ImmutableCollection;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;
import kpn.utils.TestDomain;
import kpn.utils.TestPredicate;
import kpn.utils.TestResult;
import kpn.utils.TestToResultConverter;

class PredicateDecoratorTest {
    
    private final TestDomain domain = new TestDomain(1L);
    private final Function<ServiceResult<TestDomain>, TestResult<TestDomain>> converter = new TestToResultConverter();
 
    @Test
    void shouldCheckExecution_ifServiceNull(){
        PredicateDecorator<TestPredicate, TestDomain, TestResult<TestDomain>> decorator = new PredicateDecorator<>(null, converter);
        TestResult<TestDomain> result = decorator.execute(new TestPredicate());

        TestResult<Object> expectedResult = new TestResult<>(Code.SERVICE_PREDICATE_UNSUPPORTED.getValue());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckExecution(){
        PredicateDecorator<TestPredicate, TestDomain, TestResult<TestDomain>> decorator = new PredicateDecorator<>(createServive(), converter);
        TestResult<TestDomain> result = decorator.execute(new TestPredicate());

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(new ImmutableCollection<>(domain)));
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestPredicateService createServive(){
        TestPredicateService service = Mockito.mock(TestPredicateService.class);
        Mockito
            .when(service.execute(Mockito.anyObject()))
            .thenReturn(new ImmutableServiceResult<>(new ImmutableCollection<TestDomain>(domain)));
        return service;
    }

    private abstract class TestPredicateService implements PredicateService<TestPredicate, ServiceResult<TestDomain>>{}
}
