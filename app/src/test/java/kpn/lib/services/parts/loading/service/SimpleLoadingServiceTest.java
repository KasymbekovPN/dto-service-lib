package kpn.lib.services.parts.loading.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.collection.ImmutableCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.lib.services.parts.loading.executor.LoadingAllExecutor;
import kpn.lib.services.parts.loading.executor.LoadingByIdExecutor;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;
import kpn.utils.TestDomain;

class SimpleLoadingServiceTest {
    
    @Test
    void shouldCheckFailByIdLoading(){
        ServiceResult<TestDomain> result
            = new SimpleLoadingService<Long, TestDomain>(null, null).byId(1L);

        assertThat(result).isEqualTo(new ImmutableServiceResult<>("executor.loadingById.method.load.unsupported"));
    }

    @Test
    void shouldCheckByIdLoading() throws DTOServiceException{
        ServiceResult<TestDomain> result
            = new SimpleLoadingService<Long, TestDomain>(createByIdExecutor(), null).byId(1L);

        assertThat(result).isEqualTo(new ImmutableServiceResult<>(new ImmutableCollection<>()));
    }

    @Test
    void shouldCheckFailAllLoading(){
        ServiceResult<TestDomain> result
            = new SimpleLoadingService<Long, TestDomain>(null, null).all();

        assertThat(result).isEqualTo(new ImmutableServiceResult<>("executor.loadingAll.method.load.unsupported"));
    }

    @Test
    void shouldCheckAllLoading() throws DTOServiceException{
        ServiceResult<TestDomain> result
            = new SimpleLoadingService<Long, TestDomain>(null, createAllExecutor()).all();

        assertThat(result).isEqualTo(new ImmutableServiceResult<>(new ImmutableCollection<>()));
    }

    private TestLoadingByIdExecutor createByIdExecutor() throws DTOServiceException{
        TestLoadingByIdExecutor executor = Mockito.mock(TestLoadingByIdExecutor.class);
        Mockito
            .when(executor.load(Mockito.anyObject()))
            .thenReturn(new ImmutableCollection<>());
        return executor;
    }

    private TestLoadingAllExecutor createAllExecutor() throws DTOServiceException{
        TestLoadingAllExecutor executor = Mockito.mock(TestLoadingAllExecutor.class);
        Mockito
            .when(executor.load())
            .thenReturn(new ImmutableCollection<>());
        return executor;
    }

    private abstract class TestLoadingByIdExecutor implements LoadingByIdExecutor<Long, TestDomain>{}
    private abstract class TestLoadingAllExecutor implements LoadingAllExecutor<TestDomain> {}
}
