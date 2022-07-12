package kpn.lib.executor.loading;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.lib.exception.DTOException;
import kpn.utils.TestDomain;

class DefaultCompletelyLoadingExecutorTest {
    
    @Test
    void shouldCheckLoading(){
        DefaultCompletelyLoadingExecutor<TestDomain> executor = new DefaultCompletelyLoadingExecutor<TestDomain>();
        Throwable throwable = Assertions.catchThrowable(() -> {
            executor.load();
        });
        Assertions.assertThat(throwable).isInstanceOf(DTOException.class);
    }
}
