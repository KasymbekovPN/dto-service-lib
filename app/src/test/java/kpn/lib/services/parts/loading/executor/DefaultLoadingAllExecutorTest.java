package kpn.lib.services.parts.loading.executor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.TestDomain;

class DefaultLoadingAllExecutorTest {
    
    @Test
    void shouldCheckLoading(){
        DefaultLoadingAllExecutor<TestDomain> executor = new DefaultLoadingAllExecutor<TestDomain>();
        Throwable throwable = Assertions.catchThrowable(() -> {
            executor.load();
        });
        Assertions.assertThat(throwable).isInstanceOf(DTOServiceException.class);
    }
}
