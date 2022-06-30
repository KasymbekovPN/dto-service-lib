package kpn.lib.services.parts.deleting.executor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.TestDomain;

class DefaultDeletingByIdExecutorTest {
    
    @Test
    void shouldCheckDeleting(){
        DefaultDeletingByIdExecutor<Long, TestDomain> executor = new DefaultDeletingByIdExecutor<Long, TestDomain>();

        Throwable throwable = Assertions.catchThrowable(() -> {
            executor.delete(1L);
        });
        Assertions.assertThat(throwable).isInstanceOf(DTOServiceException.class);
    }
}
