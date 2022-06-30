package kpn.lib.deleter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import kpn.lib.collection.Collection;
import kpn.lib.collection.ImmutableCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.EDConvUtils;
import kpn.utils.MultiConvUtils;
import kpn.utils.TestDomain;
import kpn.utils.TestEntity;

class SimpleDeleteServiceTest {

    @Test
    void shouldCheckFailByIdDeleting(){
        SimpleDeleteService<Long, TestEntity, TestDomain, String> service = new SimpleDeleteService<>(
            new TestDeleterById(false),
            null,
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.byId(1L);
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    void shouldCheckByIdDeleting(){
        SimpleDeleteService<Long, TestEntity, TestDomain, String> service = new SimpleDeleteService<>(
            new TestDeleterById(true),
            null,
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.byId(1L);
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
    }

    @Test
    void shouldCheckFailFullDeleting(){
        SimpleDeleteService<Long, TestEntity, TestDomain, String> service = new SimpleDeleteService<>(
            null,
            new TestDeleterAll(false),
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.all();
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    void shouldCheckFullDeleting(){
        SimpleDeleteService<Long, TestEntity, TestDomain, String> service = new SimpleDeleteService<>(
            null,
            new TestDeleterAll(true),
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.all();
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
    }

    private static class TestDeleterById implements DeleterByIdOld<Long, TestEntity>{
        private final boolean success;

        public TestDeleterById(boolean success) {
            this.success = success;
        }

        @Override
        public Collection<TestEntity> delete(Long id) throws DTOServiceException {
            if (success){
                return new ImmutableCollection<>(new TestEntity(0L));
            }
            throw new DTOServiceException("");
        }
    }

    private static class TestDeleterAll implements DeleterAllOld<Long, TestEntity>{
        private final boolean success;

        public TestDeleterAll(boolean success) {
            this.success = success;
        }

        @Override
        public Collection<TestEntity> delete() throws DTOServiceException {
            if (success){
                return new ImmutableCollection<>(new TestEntity(0L));
            }
            throw new DTOServiceException("");
        }
    }
}
