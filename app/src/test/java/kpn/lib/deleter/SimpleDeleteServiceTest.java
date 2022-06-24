package kpn.lib.deleter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.collection.DomainCollection;
import kpn.lib.collection.ImmutableDomainCollection;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.AbstractDomain;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleDeleteServiceTest {

    private static final String SUCCESS_RESULT = "success";
    private static final String FAIL_RESULT = "fail";

    @Test
    public void shouldCheckFailByIdDeleting(){
        SimpleDeleteService<Long, TestDomain, String> service = 
            new SimpleDeleteService<>(new TestDeleterById(false), null, createConverter());

        String result = service.byId(1L);
        assertThat(result).isEqualTo(FAIL_RESULT);
    }

    @Test
    public void shouldCheckByIdDeleting(){
        SimpleDeleteService<Long, TestDomain, String> service = 
            new SimpleDeleteService<>(new TestDeleterById(true), null, createConverter());

        String result = service.byId(1L);
        assertThat(result).isEqualTo(SUCCESS_RESULT);
    }

    @Test
    public void shouldCheckFailFullDeleting(){
        SimpleDeleteService<Long, TestDomain, String> service = 
            new SimpleDeleteService<>(null, new TestDeleterAll(false), createConverter());

        String result = service.all();
        assertThat(result).isEqualTo(FAIL_RESULT);
    }

    @Test
    public void shouldCheckFullDeleting(){
        SimpleDeleteService<Long, TestDomain, String> service = 
            new SimpleDeleteService<>(null, new TestDeleterAll(true), createConverter());

        String result = service.all();
        assertThat(result).isEqualTo(SUCCESS_RESULT);
    }

    // TODO: move
    private TestConverter createConverter(){
        TestConverter converter = Mockito.mock(TestConverter.class);
        Mockito
            .when(converter.convertValue(Mockito.anyObject()))
            .thenReturn(SUCCESS_RESULT);
        Mockito
            .when(converter.convertException(Mockito.anyObject()))
            .thenReturn(FAIL_RESULT);
        return converter;
    }

    // TODO: move
    private static class TestDomain extends AbstractDomain<Long>{
        public TestDomain(long id) {
            setId(id);
        }
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

    // TODO: move
    private abstract class TestConverter implements MultiConverter<DomainCollection<TestDomain>, String>{}
}
