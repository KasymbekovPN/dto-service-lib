package kpn.lib.saver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import kpn.lib.collection.DomainCollection;
import kpn.lib.collection.ImmutableDomainCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.utils.MultiConvUtils;
import kpn.utils.TestDomain;

public class SimpleSaverServiceTest {

    @Test
    public void shouldCheckFailSaving(){
        SimpleSaveService<Long, TestDomain, String> service 
            = new SimpleSaveService<>(new TestSaver(false), MultiConvUtils.create());

        String result = service.save(new TestDomain(0L));
        assertThat(result).isEqualTo(MultiConvUtils.FAIL_RESULT);
    }

    @Test
    public void shouldCheckSuccessSaving(){
        SimpleSaveService<Long, TestDomain, String> service 
            = new SimpleSaveService<>(new TestSaver(true), MultiConvUtils.create());

        String result = service.save(new TestDomain(0L));
        assertThat(result).isEqualTo(MultiConvUtils.SUCCESS_RESULT);
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
}
