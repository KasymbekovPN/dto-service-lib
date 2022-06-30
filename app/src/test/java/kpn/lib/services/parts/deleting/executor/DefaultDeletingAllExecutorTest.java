package kpn.lib.services.parts.deleting.executor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.TestDomain;

class DefaultDeletingAllExecutorTest {
    
    @Test
    void shouldCheckDeleting(){
        DefaultDeletingAllExecutor<TestDomain> executor = new DefaultDeletingAllExecutor<>();

        Throwable throwable = Assertions.catchThrowable(() -> {
            executor.delete();
        });
        Assertions.assertThat(throwable).isInstanceOf(DTOServiceException.class);
    }
}
