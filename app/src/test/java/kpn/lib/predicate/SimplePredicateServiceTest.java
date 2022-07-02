package kpn.lib.predicate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import kpn.lib.collection.Collection;
import kpn.lib.collection.ImmutableCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.EDConvUtils;
import kpn.utils.MultiConvUtils;
import kpn.utils.TestDomain;
import kpn.utils.TestEntity;

class SimplePredicateServiceTest {

    @Test
    void shouldCheckFailExecution(){
        SimplePredicateService<Long, TestEntity, TestDomain, Integer, String> service = new SimplePredicateService<>(
            new TestPredicateExecutor(false),
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.execute(1);
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    void shouldCheckExecution(){
        SimplePredicateService<Long, TestEntity, TestDomain, Integer, String> service = new SimplePredicateService<>(
            new TestPredicateExecutor(true),
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.execute(1);
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
    }

    private static class TestPredicateExecutor implements PredicateExecutorOld<Integer, Long, TestEntity>{
        private final boolean success;

        public TestPredicateExecutor(boolean success) {
            this.success = success;
        }

        @Override
        public Collection<TestEntity> execute(Integer predicate) throws DTOServiceException {
            if (success){
                return new ImmutableCollection<>(new TestEntity(0L));
            }
            throw new DTOServiceException("");
        }
    }
}
