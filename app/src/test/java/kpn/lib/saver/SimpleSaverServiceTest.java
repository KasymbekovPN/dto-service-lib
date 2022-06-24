package kpn.lib.saver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.collection.DomainCollection;
import kpn.lib.collection.ImmutableDomainCollection;
import kpn.lib.converter.MultiConverter;
import kpn.lib.domains.AbstractDomain;
import kpn.lib.exceptions.DTOServiceException;

public class SimpleSaverServiceTest {
    
    private static final String SUCCESS_RESULT = "success";
    private static final String FAIL_RESULT = "fail";

    @Test
    public void shouldCheckFailSaving(){
        SimpleSaveService<Long, TestDomain, String> service 
            = new SimpleSaveService<>(new TestSaver(false), createConverter());

        String result = service.save(new TestDomain(0L));
        assertThat(result).isEqualTo(FAIL_RESULT);
    }

    @Test
    public void shouldCheckSuccessSaving(){
        SimpleSaveService<Long, TestDomain, String> service 
            = new SimpleSaveService<>(new TestSaver(true), createConverter());

        String result = service.save(new TestDomain(0L));
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

    private static class TestSaver implements Saver<Long, TestDomain>{
        private final boolean success;

        public TestSaver(boolean success) {
            this.success = success;
        }

        @Override
        public DomainCollection<TestDomain> save(TestDomain domain) throws DTOServiceException {
            if (success){
                return new ImmutableDomainCollection<>(new TestDomain(0L));
            }
            throw new DTOServiceException("");
        }
    }

    private abstract class TestConverter implements MultiConverter<DomainCollection<TestDomain>, String>{}
}
