package kpn.lib.services.parts.predicate.executor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.TestDomain;
import kpn.utils.TestPredicate;

class DefaultPredicateExecutorTest {

    @Test
    void shouldCheckExecution(){
        DefaultPredicateExecutor<TestPredicate, TestDomain> executor = new DefaultPredicateExecutor<>();
        Throwable throwable = Assertions.catchThrowable(() -> {
            executor.execute(new TestPredicate());
        });

        Assertions.assertThat(throwable).isInstanceOf(DTOServiceException.class);
    }
}
