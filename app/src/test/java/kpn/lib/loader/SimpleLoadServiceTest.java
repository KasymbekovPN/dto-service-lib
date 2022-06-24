package kpn.lib.loader;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.collection.DomainCollection;
import kpn.lib.collection.ImmutableDomainCollection;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.AbstractDomain;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleLoadServiceTest {
    
    private static final String SUCCESS_RESULT = "success";
    private static final String FAIL_RESULT = "fail";

    @Test
    public void shouldCheckFailLoadingById(){
        SimpleLoadService<Long, TestDomain, String> service = new SimpleLoadService<>(
            new TestLoaderById(false),
            null,
            createConverter()
        );

        String result = service.byId(1L);
        assertThat(result).isEqualTo(FAIL_RESULT);
    }

    @Test
    public void shouldCheckLoadingById(){
        SimpleLoadService<Long, TestDomain, String> service = new SimpleLoadService<>(
            new TestLoaderById(true),
            null,
            createConverter()
        );

        String result = service.byId(1L);
        assertThat(result).isEqualTo(SUCCESS_RESULT);
    }

    @Test
    public void shouldCheckFailFullLoading(){
        SimpleLoadService<Long, TestDomain, String> service = new SimpleLoadService<>(
            null,
            new TestLoaderAll(false),
            createConverter()
        );

        String result = service.all();
        assertThat(result).isEqualTo(FAIL_RESULT);
    }

    @Test
    public void shouldCheckFullLoading() throws DTOServiceException{
        SimpleLoadService<Long, TestDomain, String> service = new SimpleLoadService<>(
            null,
            new TestLoaderAll(true),
            createConverter()
        );

        String result = service.all();
        assertThat(result).isEqualTo(SUCCESS_RESULT);
    }

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

    private static class TestDomain extends AbstractDomain<Long>{
        public TestDomain(long id) {
            setId(id);
        }
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

    private abstract class TestConverter implements MultiConverter<DomainCollection<TestDomain>, String>{}
}
