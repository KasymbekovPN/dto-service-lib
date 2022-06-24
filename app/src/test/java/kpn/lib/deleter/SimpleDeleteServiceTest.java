package kpn.lib.deleter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import kpn.lib.collection.DomainCollection;
import kpn.lib.collection.ImmutableDomainCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.MultiConvUtils;
import kpn.utils.TestDomain;

public class SimpleDeleteServiceTest {

    @Test
    public void shouldCheckFailByIdDeleting(){
        SimpleDeleteService<Long, TestDomain, String> service = 
            new SimpleDeleteService<>(new TestDeleterById(false), null, MultiConvUtils.create());

        String result = service.byId(1L);
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    public void shouldCheckByIdDeleting(){
        SimpleDeleteService<Long, TestDomain, String> service = 
            new SimpleDeleteService<>(new TestDeleterById(true), null, MultiConvUtils.create());

        String result = service.byId(1L);
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
    }

    @Test
    public void shouldCheckFailFullDeleting(){
        SimpleDeleteService<Long, TestDomain, String> service = 
            new SimpleDeleteService<>(null, new TestDeleterAll(false), MultiConvUtils.create());

        String result = service.all();
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    public void shouldCheckFullDeleting(){
        SimpleDeleteService<Long, TestDomain, String> service = 
            new SimpleDeleteService<>(null, new TestDeleterAll(true), MultiConvUtils.create());

        String result = service.all();
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
    }

    private static class TestDeleterById implements DeleterById<Long, TestDomain>{
        private final boolean success;

        public TestDeleterById(boolean success) {
            this.success = success;
        }

        @Override
        public DomainCollection<TestDomain> delete(Long id) throws DTOServiceException {
            if (success){
                return new ImmutableDomainCollection<>(new TestDomain(0L));
            }
            throw new DTOServiceException("");
        }
    }

    private static class TestDeleterAll implements DeleterAll<Long, TestDomain>{
        private final boolean success;

        public TestDeleterAll(boolean success) {
            this.success = success;
        }

        @Override
        public DomainCollection<TestDomain> delete() throws DTOServiceException {
            if (success){
                return new ImmutableDomainCollection<>(new TestDomain(0L));
            }
            throw new DTOServiceException("");
        }
    }
}
