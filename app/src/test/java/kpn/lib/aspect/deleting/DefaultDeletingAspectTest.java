package kpn.lib.aspect.deleting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.code.ErrorCode;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.deleting.CompletelyDeletingExecutor;
import kpn.lib.executor.DefaultExecutorResult;
import kpn.lib.executor.deleting.ByIdDeletingExecutor;
import kpn.utils.TestDomain;

class DefaultDeletingAspectTest {
    
    @Test
    void shouldCheckFailByIdDeleting(){
        DefaultDeletingAspect<Long, TestDomain> service 
            = new DefaultDeletingAspect<>(null, null);

        assertThat(service.byId(1L))
            .isEqualTo(new DefaultAspectResult<TestDomain>(ErrorCode.EXECUTOR_DELETING_BY_ID_UNSUPPORTED.getValue()));
    }

    @Test
    void shouldCheckByIdDeleting() throws DTOException{
        DefaultDeletingAspect<Long, TestDomain> service 
            = new DefaultDeletingAspect<>(createByIdExecutor(), null);

        assertThat(service.byId(1L))
            .isEqualTo(new DefaultAspectResult<TestDomain>(new DefaultExecutorResult<>()));
    }

    @Test
    void shouldCheckFailAllDeleting(){
        DefaultDeletingAspect<Long, TestDomain> service 
            = new DefaultDeletingAspect<>(null, null);

        assertThat(service.all())
            .isEqualTo(new DefaultAspectResult<TestDomain>(ErrorCode.EXECUTOR_DELETING_ALL_UNSUPPORTED.getValue()));
    }

    @Test
    void shouldCheckAllDeleting() throws DTOException{
        DefaultDeletingAspect<Long, TestDomain> service 
            = new DefaultDeletingAspect<>(null, createAllExecutor());

        assertThat(service.all())
            .isEqualTo(new DefaultAspectResult<TestDomain>(new DefaultExecutorResult<>()));
    }

    private TestDeletingByIdExecutor createByIdExecutor() throws DTOException{
        TestDeletingByIdExecutor executor = Mockito.mock(TestDeletingByIdExecutor.class);
        Mockito
            .when(executor.delete(Mockito.anyObject()))
            .thenReturn(new DefaultExecutorResult<>());
        return executor;
    }

    private TestDeletingAllExecutor createAllExecutor() throws DTOException{
        TestDeletingAllExecutor executor = Mockito.mock(TestDeletingAllExecutor.class);
        Mockito
            .when(executor.delete())
            .thenReturn(new DefaultExecutorResult<>());
        return executor;
    }

    private abstract class TestDeletingByIdExecutor implements ByIdDeletingExecutor<Long, TestDomain>{}
    private abstract class TestDeletingAllExecutor implements CompletelyDeletingExecutor<TestDomain>{}
}
