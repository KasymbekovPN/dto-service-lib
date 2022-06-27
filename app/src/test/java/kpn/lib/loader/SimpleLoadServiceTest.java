package kpn.lib.loader;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import kpn.lib.collection.Collection;
import kpn.lib.collection.ImmutableCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.EDConvUtils;
import kpn.utils.MultiConvUtils;
import kpn.utils.TestDomain;
import kpn.utils.TestEntity;

class SimpleLoadServiceTest {

    @Test
    void shouldCheckFailLoadingById(){
        SimpleLoadService<Long, TestEntity, TestDomain, String> service = new SimpleLoadService<>(
            new TestLoaderById(false),
            null,
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.byId(1L);
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    void shouldCheckLoadingById(){
        SimpleLoadService<Long, TestEntity, TestDomain, String> service = new SimpleLoadService<>(
            new TestLoaderById(true),
            null,
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.byId(1L);
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
    }

    @Test
    void shouldCheckFailFullLoading(){
        SimpleLoadService<Long, TestEntity, TestDomain, String> service = new SimpleLoadService<>(
            null,
            new TestLoaderAll(false),
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.all();
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    void shouldCheckFullLoading() throws DTOServiceException{
        SimpleLoadService<Long, TestEntity, TestDomain, String> service = new SimpleLoadService<>(
            null,
            new TestLoaderAll(true),
            EDConvUtils.create(),
            MultiConvUtils.create()
        );

        String result = service.all();
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
    }

    private static class TestLoaderById implements LoaderById<Long, TestEntity>{
        private final boolean success;

        public TestLoaderById(boolean success) {
            this.success = success;
        }

        @Override
        public Collection<TestEntity> load(Long id) throws DTOServiceException {
            if (success){
                return new ImmutableCollection<TestEntity>(new TestEntity(id));
            }
            throw new DTOServiceException("");
        }
    }

    private static class TestLoaderAll implements LoaderAll<Long, TestEntity>{
        private final boolean success;

        public TestLoaderAll(boolean success) {
            this.success = success;
        }

        @Override
        public Collection<TestEntity> load() throws DTOServiceException {
            if (success){
                return new ImmutableCollection<TestEntity>(new TestEntity(0L));
            }
            throw new DTOServiceException("");
        }
    }
}
