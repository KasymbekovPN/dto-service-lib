package kpn.lib.executor.deleting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.lib.exception.DTOException;
import kpn.utils.TestDomain;

class DefaultCompletelyDeletingExecutorTest {
    
    @Test
    void shouldCheckDeleting(){
        DefaultCompletelyDeletingExecutor<TestDomain> executor = new DefaultCompletelyDeletingExecutor<>();

        Throwable throwable = Assertions.catchThrowable(() -> {
            executor.delete();
        });
        Assertions.assertThat(throwable).isInstanceOf(DTOException.class);
    }
}
