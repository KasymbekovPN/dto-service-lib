package kpn.lib.loader;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import kpn.lib.collection.DomainCollection;
import kpn.lib.collection.ImmutableDomainCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.MultiConvUtils;
import kpn.utils.TestDomain;

public class SimpleLoadServiceTest {


    @Test
    public void shouldCheckFailLoadingById(){
        SimpleLoadService<Long, TestDomain, String> service = new SimpleLoadService<>(
            new TestLoaderById(false),
            null,
            MultiConvUtils.create()
        );

        String result = service.byId(1L);
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    public void shouldCheckLoadingById(){
        SimpleLoadService<Long, TestDomain, String> service = new SimpleLoadService<>(
            new TestLoaderById(true),
            null,
            MultiConvUtils.create()
        );

        String result = service.byId(1L);
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
    }

    @Test
    public void shouldCheckFailFullLoading(){
        SimpleLoadService<Long, TestDomain, String> service = new SimpleLoadService<>(
            null,
            new TestLoaderAll(false),
            MultiConvUtils.create()
        );

        String result = service.all();
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    public void shouldCheckFullLoading() throws DTOServiceException{
        SimpleLoadService<Long, TestDomain, String> service = new SimpleLoadService<>(
            null,
            new TestLoaderAll(true),
            MultiConvUtils.create()
        );

        String result = service.all();
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
    }

    private static class TestLoaderById implements LoaderById<Long, TestDomain>{
        private final boolean success;

        public TestLoaderById(boolean success) {
            this.success = success;
        }

        @Override
        public DomainCollection<TestDomain> load(Long id) throws DTOServiceException {
            if (success){
                return new ImmutableDomainCollection<TestDomain>(new TestDomain(id));
            }
            throw new DTOServiceException("");
        }
    }

    private static class TestLoaderAll implements LoaderAll<Long, TestDomain>{
        private final boolean success;

        public TestLoaderAll(boolean success) {
            this.success = success;
        }

        @Override
        public DomainCollection<TestDomain> load() throws DTOServiceException {
            if (success){
                return new ImmutableDomainCollection<TestDomain>(new TestDomain(0L));
            }
            throw new DTOServiceException("");
        }
    }
}
