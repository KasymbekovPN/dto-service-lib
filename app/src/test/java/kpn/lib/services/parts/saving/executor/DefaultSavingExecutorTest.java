package kpn.lib.services.parts.saving.executor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.TestDomain;

class DefaultSavingExecutorTest {
    
    @Test
    void shouldCheckSaving(){
        DefaultSavingExecutor<TestDomain> executor = new DefaultSavingExecutor<>();
        Throwable throwable = Assertions.catchThrowable(() -> {
            executor.save(new TestDomain());
        });
        Assertions.assertThat(throwable).isInstanceOf(DTOServiceException.class);
    }
}
