package kpn.lib.services.parts.deleting.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.code.Code;
import kpn.lib.collection.ImmutableCollection;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;
import kpn.utils.TestDomain;
import kpn.utils.TestResult;
import kpn.utils.TestToResultConverter;

class DeletingDecoratorTest {
    private final Function<ServiceResult<TestDomain>, TestResult<TestDomain>> converter = new TestToResultConverter();

    @Test
    void shouldCheckFailDeleting_byId(){
        DeletingDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new DeletingDecorator<>(null, converter);
        TestResult<TestDomain> result = decorator.byId(1L);

        TestResult<Object> expectedResult = new TestResult<>(Code.SERVICE_DELETING_BY_ID_UNSUPPORTED.getValue());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckDeleting_byId(){
        DeletingDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new DeletingDecorator<>(createService(), converter);
        TestResult<TestDomain> result = decorator.byId(1L);

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(new ImmutableCollection<>()));
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckFailDeleting_all(){
        DeletingDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new DeletingDecorator<>(null, converter);
        TestResult<TestDomain> result = decorator.all();

        TestResult<Object> expectedResult = new TestResult<>(Code.SERVICE_DELETING_ALL_UNSUPPORTED.getValue());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckDeleting_all(){
        DeletingDecorator<Long, TestDomain, TestResult<TestDomain>> decorator = new DeletingDecorator<>(createService(), converter);
        TestResult<TestDomain> result = decorator.all();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(new ImmutableCollection<>()));
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestDeletingService createService(){
        ImmutableServiceResult<TestDomain> result = new ImmutableServiceResult<>(new ImmutableCollection<TestDomain>());

        TestDeletingService service = Mockito.mock(TestDeletingService.class);
        Mockito
            .when(service.byId(1L))
            .thenReturn(result);
        Mockito
            .when(service.all())
            .thenReturn(result);
        return service;
    }

    private abstract class TestDeletingService implements DeletingService<Long, ServiceResult<TestDomain>> {}
}
