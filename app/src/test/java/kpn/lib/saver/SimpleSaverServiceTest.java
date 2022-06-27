package kpn.lib.saver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import kpn.lib.collection.Collection;
import kpn.lib.collection.ImmutableCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.EDConvUtils;
import kpn.utils.MultiConvUtils;
import kpn.utils.TestDomain;
import kpn.utils.TestEntity;

class SimpleSaverServiceTest {

    @Test
    void shouldCheckFailSaving(){
        SimpleSaveService<Long, TestDomain, TestEntity, String> service = new SimpleSaveService<>(
            new TestSaver(false),
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.save(new TestDomain(0L));
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    void shouldCheckSuccessSaving(){
        SimpleSaveService<Long, TestDomain, TestEntity, String> service = new SimpleSaveService<>(
            new TestSaver(true),
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.save(new TestDomain(0L));
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
    }

    private static class TestSaver implements Saver<Long, TestEntity>{
        private final boolean success;

        public TestSaver(boolean success) {
            this.success = success;
        }

        @Override
        public Collection<TestEntity> save(TestEntity entity) throws DTOServiceException {
            if (success){
                return new ImmutableCollection<TestEntity>(new TestEntity(0L));
            }
            throw new DTOServiceException("");
        }
    }
}
