package kpn.lib.aspect.deleting;

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

class DeletingAspectDecoratorTest {
    private final Function<AspectResult<TestDomain>, TestResult<TestDomain>> converter = new TestToResultConverter();

    @Test
    void shouldCheckFailDeleting_byId(){
        DeletingAspectDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new DeletingAspectDecorator<>(null, converter);
        TestResult<TestDomain> result = decorator.byId(1L);

        TestResult<TestDomain> expectedResult = new TestResult<>(ErrorCode.SERVICE_DELETING_BY_ID_UNSUPPORTED.getValue());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckDeleting_byId(){
        DeletingAspectDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new DeletingAspectDecorator<>(createService(), converter);
        TestResult<TestDomain> result = decorator.byId(1L);

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(new DefaultExecutorResult<>()));
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckFailDeleting_all(){
        DeletingAspectDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new DeletingAspectDecorator<>(null, converter);
        TestResult<TestDomain> result = decorator.all();

        TestResult<TestDomain> expectedResult = new TestResult<>(ErrorCode.SERVICE_DELETING_ALL_UNSUPPORTED.getValue());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckDeleting_all(){
        DeletingAspectDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new DeletingAspectDecorator<>(createService(), converter);
        TestResult<TestDomain> result = decorator.all();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(new DefaultExecutorResult<>()));
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestDeletingService createService(){
        DefaultAspectResult<TestDomain> result = new DefaultAspectResult<>(new DefaultExecutorResult<TestDomain>());

        TestDeletingService service = Mockito.mock(TestDeletingService.class);
        Mockito
            .when(service.byId(1L))
            .thenReturn(result);
        Mockito
            .when(service.all())
            .thenReturn(result);
        return service;
    }

    private abstract class TestDeletingService implements DeletingAspect<Long, AspectResult<TestDomain>> {}
}
