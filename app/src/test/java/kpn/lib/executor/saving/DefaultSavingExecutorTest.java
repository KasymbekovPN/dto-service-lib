package kpn.lib.executor.saving;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.lib.exception.DTOException;
import kpn.utils.TestDomain;

class DefaultSavingExecutorTest {
    
    @Test
    void shouldCheckSaving(){
        DefaultSavingExecutor<TestDomain> executor = new DefaultSavingExecutor<>();
        Throwable throwable = Assertions.catchThrowable(() -> {
            executor.save(new TestDomain());
        });
        Assertions.assertThat(throwable).isInstanceOf(DTOException.class);
    }
}
