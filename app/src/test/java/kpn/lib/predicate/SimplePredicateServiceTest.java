package kpn.lib.predicate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import kpn.lib.collection.DomainCollection;
import kpn.lib.collection.ImmutableDomainCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.MultiConvUtils;
import kpn.utils.TestDomain;

public class SimplePredicateServiceTest {

    @Test
    public void shouldCheckFailExecution(){
        SimplePredicateService<Long, TestDomain, Integer, String> service 
            = new SimplePredicateService<>(new TestPredicateExecutor(false), MultiConvUtils.create());

        String result = service.execute(1);
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    public void shouldCheckExecution(){
        SimplePredicateService<Long, TestDomain, Integer, String> service 
            = new SimplePredicateService<>(new TestPredicateExecutor(true), MultiConvUtils.create());

        String result = service.execute(1);
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
    }

    private static class TestPredicateExecutor implements PredicateExecutor<Integer, Long, TestDomain>{
        private final boolean success;

        public TestPredicateExecutor(boolean success) {
            this.success = success;
        }

        @Override
        public DomainCollection<TestDomain> execute(Integer predicate) throws DTOServiceException {
            if (success){
                return new ImmutableDomainCollection<>(new TestDomain(0L));
            }
            throw new DTOServiceException("");
        }
    }
}
