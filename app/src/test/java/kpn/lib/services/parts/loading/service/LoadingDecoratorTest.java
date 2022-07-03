package kpn.lib.services.parts.loading.service;

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

class LoadingDecoratorTest {
    private final TestDomain domain = new TestDomain(1L);
    private final Function<ServiceResult<TestDomain>, TestResult<TestDomain>> converter = new TestToResultConverter();
    
    @Test
    void shouldCheckByIdExecution_ifServiceNull(){
        LoadingDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new LoadingDecorator<>(null, converter);
        TestResult<TestDomain> result = decorator.byId(1L);

        TestResult<Object> expectedResult = new TestResult<>("service.loading.byId.absent");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckByIdExecution(){
        LoadingDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new LoadingDecorator<>(createService(), converter);
        TestResult<TestDomain> result = decorator.byId(1L);

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(new ImmutableCollection<>(domain)));
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckAllExecution_ifServiceNull(){
        LoadingDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new LoadingDecorator<>(null, converter);
        TestResult<TestDomain> result = decorator.all();

        TestResult<Object> expectedResult = new TestResult<>("service.loading.all.absent");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckAllExecution(){
        LoadingDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new LoadingDecorator<>(createService(), converter);
        TestResult<TestDomain> result = decorator.all();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(new ImmutableCollection<>(domain)));
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestLoadingService createService(){
        ImmutableServiceResult<TestDomain> result = new ImmutableServiceResult<>(new ImmutableCollection<TestDomain>(domain));

        TestLoadingService service = Mockito.mock(TestLoadingService.class);
        Mockito
            .when(service.byId(Mockito.anyLong()))
            .thenReturn(result);
        Mockito
            .when(service.all())
            .thenReturn(result);
        return service;
    }

    private abstract class TestLoadingService implements LoadingService<Long, ServiceResult<TestDomain>> {}
}
