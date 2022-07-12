package kpn.lib.aspect.loading;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.code.ErrorCode;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.DefaultExecutorResult;
import kpn.lib.executor.loading.CompletelyLoadingExecutor;
import kpn.lib.executor.loading.ByIdLoadingExecutor;
import kpn.utils.TestDomain;

class DefaultLoadingAspectTest {
    
    @Test
    void shouldCheckFailByIdLoading(){
        AspectResult<TestDomain> result
            = new DefaultLoadingAspect<Long, TestDomain>(null, null).byId(1L);

        assertThat(result).isEqualTo(new DefaultAspectResult<>(ErrorCode.EXECUTOR_LOADING_BY_ID_UNSUPPORTED.getValue()));
    }

    @Test
    void shouldCheckByIdLoading() throws DTOException{
        AspectResult<TestDomain> result
            = new DefaultLoadingAspect<Long, TestDomain>(createByIdExecutor(), null).byId(1L);

        assertThat(result).isEqualTo(new DefaultAspectResult<>(new DefaultExecutorResult<>()));
    }

    @Test
    void shouldCheckFailAllLoading(){
        AspectResult<TestDomain> result
            = new DefaultLoadingAspect<Long, TestDomain>(null, null).all();

        assertThat(result).isEqualTo(new DefaultAspectResult<>(ErrorCode.EXECUTOR_LOADING_ALL_UNSUPPORTED.getValue()));
    }

    @Test
    void shouldCheckAllLoading() throws DTOException{
        AspectResult<TestDomain> result
            = new DefaultLoadingAspect<Long, TestDomain>(null, createAllExecutor()).all();

        assertThat(result).isEqualTo(new DefaultAspectResult<>(new DefaultExecutorResult<>()));
    }

    private TestLoadingByIdExecutor createByIdExecutor() throws DTOException{
        TestLoadingByIdExecutor executor = Mockito.mock(TestLoadingByIdExecutor.class);
        Mockito
            .when(executor.load(Mockito.anyObject()))
            .thenReturn(new DefaultExecutorResult<>());
        return executor;
    }

    private TestLoadingAllExecutor createAllExecutor() throws DTOException{
        TestLoadingAllExecutor executor = Mockito.mock(TestLoadingAllExecutor.class);
        Mockito
            .when(executor.load())
            .thenReturn(new DefaultExecutorResult<>());
        return executor;
    }

    private abstract class TestLoadingByIdExecutor implements ByIdLoadingExecutor<Long, TestDomain>{}
    private abstract class TestLoadingAllExecutor implements CompletelyLoadingExecutor<TestDomain> {}
}
