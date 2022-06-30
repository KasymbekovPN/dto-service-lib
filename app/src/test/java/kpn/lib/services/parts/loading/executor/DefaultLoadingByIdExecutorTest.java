package kpn.lib.services.parts.loading.executor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.TestDomain;

class DefaultLoadingByIdExecutorTest {
    
    @Test
    void shouldCheckLoading(){
        DefaultLoadingByIdExecutor<Long, TestDomain> executor = new DefaultLoadingByIdExecutor<Long, TestDomain>();
        
        Throwable throwable = Assertions.catchThrowable(() -> {
            executor.load(1L);
        });
        Assertions.assertThat(throwable).isInstanceOf(DTOServiceException.class);
    }
}
