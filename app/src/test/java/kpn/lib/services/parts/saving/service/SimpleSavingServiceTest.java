package kpn.lib.services.parts.saving.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.collection.ImmutableCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.lib.services.parts.saving.executor.SavingExecutor;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;
import kpn.utils.TestDomain;

class SimpleSavingServiceTest {
    private static final Long ID = 1L;

    @Test
    void shouldCheckFailSaving(){
        ServiceResult<TestDomain> result = new SimpleSavingService<TestDomain>(null).save(new TestDomain(ID));

        assertThat(result).isEqualTo(new ImmutableServiceResult<>("executor.saving.method.save.unsupported"));
    }

    @Test
    void shouldCheckSuccessSaving() throws DTOServiceException{
        TestDomain domain = new TestDomain(ID);
        ServiceResult<TestDomain> result = new SimpleSavingService<>(createExecutor(domain)).save(domain);

        assertThat(result).isEqualTo(new ImmutableServiceResult<>(new ImmutableCollection<>(domain)));
    }

    private TestSavingExecutor createExecutor(TestDomain domain) throws DTOServiceException{
        TestSavingExecutor executor = Mockito.mock(TestSavingExecutor.class);
        Mockito
            .when(executor.save(domain))
            .thenReturn(new ImmutableCollection<>(domain));
        return executor;
    }

    private abstract class TestSavingExecutor implements SavingExecutor<TestDomain>{}
}
