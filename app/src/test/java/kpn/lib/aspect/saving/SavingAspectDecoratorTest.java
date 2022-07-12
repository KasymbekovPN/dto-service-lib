package kpn.lib.aspect.saving;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.code.ErrorCode;
import kpn.lib.executor.DefaultExecutorResult;
import kpn.utils.TestDomain;
import kpn.utils.TestResult;
import kpn.utils.TestToResultConverter;

class SavingAspectDecoratorTest {
    private final TestDomain domain = new TestDomain(1L);
    private final Function<AspectResult<TestDomain>, TestResult<TestDomain>> converter = new TestToResultConverter();
 
    @Test
    void shouldCheckSaving_ifSaverNull(){
        SavingAspectDecorator<TestDomain, TestResult<TestDomain>> decorator = new SavingAspectDecorator<>(null, converter);
        TestResult<TestDomain> result = decorator.save(domain);

        TestResult<TestDomain> expectedResult = new TestResult<TestDomain>(ErrorCode.SERVICE_SAVING_UNSUPPORTED.getValue());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckSaving(){
        SavingAspectDecorator<TestDomain, TestResult<TestDomain>> decorator = new SavingAspectDecorator<>(createService(), new TestToResultConverter());
        TestResult<TestDomain> result = decorator.save(domain);

        TestResult<TestDomain> expectedResult
            = converter.apply(new DefaultAspectResult<>(new DefaultExecutorResult<TestDomain>(domain)));
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestSavingService createService(){
        TestSavingService service = Mockito.mock(TestSavingService.class);
        Mockito
            .when(service.save(domain))
            .thenReturn(new DefaultAspectResult<>(new DefaultExecutorResult<TestDomain>(domain)));
        return service;
    }

    private abstract class TestSavingService implements SavingAspect<TestDomain, AspectResult<TestDomain>>{}
}
