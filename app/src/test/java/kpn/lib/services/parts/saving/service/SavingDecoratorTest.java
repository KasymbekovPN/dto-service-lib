package kpn.lib.services.parts.saving.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.collection.ImmutableCollection;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;
import kpn.utils.TestDomain;
import kpn.utils.TestResult;
import kpn.utils.TestToResultConverter;

class SavingDecoratorTest {
    private final TestDomain domain = new TestDomain(1L);
    private final Function<ServiceResult<TestDomain>, TestResult<TestDomain>> converter = new TestToResultConverter();
 
    @Test
    void shouldCheckSaving_ifSaverNull(){
        SavingDecorator<TestDomain, TestResult<TestDomain>> decorator = new SavingDecorator<>(null, converter);
        TestResult<TestDomain> result = decorator.save(domain);

        TestResult<TestDomain> expectedResult = new TestResult<TestDomain>("service.saving.absent");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckSaving(){
        SavingDecorator<TestDomain, TestResult<TestDomain>> decorator = new SavingDecorator<>(createService(), new TestToResultConverter());
        TestResult<TestDomain> result = decorator.save(domain);

        TestResult<TestDomain> expectedResult
            = converter.apply(new ImmutableServiceResult<>(new ImmutableCollection<TestDomain>(domain)));
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestSavingService createService(){
        TestSavingService service = Mockito.mock(TestSavingService.class);
        Mockito
            .when(service.save(domain))
            .thenReturn(new ImmutableServiceResult<>(new ImmutableCollection<TestDomain>(domain)));
        return service;
    }

    private abstract class TestSavingService implements SavingService<TestDomain, ServiceResult<TestDomain>>{}
}
