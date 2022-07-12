package kpn.lib.aspect.saving;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.code.ErrorCode;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.DefaultExecutorResult;
import kpn.lib.executor.saving.SavingExecutor;
import kpn.utils.TestDomain;

class DefaultSavingAspectTest {
    private static final Long ID = 1L;

    @Test
    void shouldCheckFailSaving(){
        AspectResult<TestDomain> result = new DefaultSavingAspect<TestDomain>(null).save(new TestDomain(ID));

        assertThat(result).isEqualTo(new DefaultAspectResult<>(ErrorCode.EXECUTOR_SAVING_UNSUPPORTED.getValue()));
    }

    @Test
    void shouldCheckSuccessSaving() throws DTOException{
        TestDomain domain = new TestDomain(ID);
        AspectResult<TestDomain> result = new DefaultSavingAspect<>(createExecutor(domain)).save(domain);

        assertThat(result).isEqualTo(new DefaultAspectResult<>(new DefaultExecutorResult<>(domain)));
    }

    private TestSavingExecutor createExecutor(TestDomain domain) throws DTOException{
        TestSavingExecutor executor = Mockito.mock(TestSavingExecutor.class);
        Mockito
            .when(executor.save(domain))
            .thenReturn(new DefaultExecutorResult<>(domain));
        return executor;
    }

    private abstract class TestSavingExecutor implements SavingExecutor<TestDomain>{}
}
