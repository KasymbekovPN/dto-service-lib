package kpn.lib.executor.loading;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.lib.exception.DTOException;
import kpn.utils.TestDomain;

class DefaultByIdLoadingExecutorTest {
    
    @Test
    void shouldCheckLoading(){
        DefaultByIdLoadingExecutor<Long, TestDomain> executor = new DefaultByIdLoadingExecutor<Long, TestDomain>();
        
        Throwable throwable = Assertions.catchThrowable(() -> {
            executor.load(1L);
        });
        Assertions.assertThat(throwable).isInstanceOf(DTOException.class);
    }
}
