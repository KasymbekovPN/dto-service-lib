package kpn.lib.executor.deleting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.lib.exception.DTOException;
import kpn.utils.TestDomain;

class DefaultByIdDeletingExecutorTest {
    
    @Test
    void shouldCheckDeleting(){
        DefaultByIdDeletingExecutor<Long, TestDomain> executor = new DefaultByIdDeletingExecutor<Long, TestDomain>();

        Throwable throwable = Assertions.catchThrowable(() -> {
            executor.delete(1L);
        });
        Assertions.assertThat(throwable).isInstanceOf(DTOException.class);
    }
}
