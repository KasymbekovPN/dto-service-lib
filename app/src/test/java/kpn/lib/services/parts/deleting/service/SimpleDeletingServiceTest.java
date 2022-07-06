package kpn.lib.services.parts.deleting.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.code.Code;
import kpn.lib.collection.ImmutableCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.lib.services.parts.deleting.executor.DeletingAllExecutor;
import kpn.lib.services.parts.deleting.executor.DeletingByIdExecutor;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.utils.TestDomain;

class SimpleDeletingServiceTest {
    
    @Test
    void shouldCheckFailByIdDeleting(){
        SimpleDeletingService<Long, TestDomain> service 
            = new SimpleDeletingService<>(null, null);

        assertThat(service.byId(1L))
            .isEqualTo(new ImmutableServiceResult<TestDomain>(Code.EXECUTOR_DELETING_BY_ID_UNSUPPORTED.getValue()));
    }

    @Test
    void shouldCheckByIdDeleting() throws DTOServiceException{
        SimpleDeletingService<Long, TestDomain> service 
            = new SimpleDeletingService<>(createByIdExecutor(), null);

        assertThat(service.byId(1L))
            .isEqualTo(new ImmutableServiceResult<TestDomain>(new ImmutableCollection<>()));
    }

    @Test
    void shouldCheckFailAllDeleting(){
        SimpleDeletingService<Long, TestDomain> service 
            = new SimpleDeletingService<>(null, null);

        assertThat(service.all())
            .isEqualTo(new ImmutableServiceResult<TestDomain>(Code.EXECUTOR_DELETING_ALL_UNSUPPORTED.getValue()));
    }

    @Test
    void shouldCheckAllDeleting() throws DTOServiceException{
        SimpleDeletingService<Long, TestDomain> service 
            = new SimpleDeletingService<>(null, createAllExecutor());

        assertThat(service.all())
            .isEqualTo(new ImmutableServiceResult<TestDomain>(new ImmutableCollection<>()));
    }

    private TestDeletingByIdExecutor createByIdExecutor() throws DTOServiceException{
        TestDeletingByIdExecutor executor = Mockito.mock(TestDeletingByIdExecutor.class);
        Mockito
            .when(executor.delete(Mockito.anyObject()))
            .thenReturn(new ImmutableCollection<>());
        return executor;
    }

    private TestDeletingAllExecutor createAllExecutor() throws DTOServiceException{
        TestDeletingAllExecutor executor = Mockito.mock(TestDeletingAllExecutor.class);
        Mockito
            .when(executor.delete())
            .thenReturn(new ImmutableCollection<>());
        return executor;
    }

    private abstract class TestDeletingByIdExecutor implements DeletingByIdExecutor<Long, TestDomain>{}
    private abstract class TestDeletingAllExecutor implements DeletingAllExecutor<TestDomain>{}
}
