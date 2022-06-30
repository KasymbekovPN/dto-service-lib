package kpn.lib.saver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import kpn.lib.collection.Collection;
import kpn.lib.collection.ImmutableCollection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.lib.services.parts.saving.executor.SavingExecutor;
import kpn.lib.services.parts.saving.service.SimpleSavingService;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;
import kpn.utils.TestDomain;

class SimpleSavingServiceTest {
    private static final Long ID = 1L;
    private static final String CODE = "code";

    @Test
    void shouldCheckFailSaving(){
        ServiceResult<TestDomain> result = new SimpleSavingService<>(new TestSaver(false)).save(new TestDomain(ID));

        assertThat(result).isEqualTo(new ImmutableServiceResult<>(CODE));
    }

    @Test
    void shouldCheckSuccessSaving(){
        ServiceResult<TestDomain> result = new SimpleSavingService<>(new TestSaver(true)).save(new TestDomain(ID));

        assertThat(result).isEqualTo(new ImmutableServiceResult<>(new ImmutableCollection<>(new TestDomain(ID))));
    }

    private static class TestSaver implements SavingExecutor<TestDomain>{
        private final boolean success;

        public TestSaver(boolean success) {
            this.success = success;
        }

        @Override
        public Collection<TestDomain> save(TestDomain domain) throws DTOServiceException {
            if (success){
                return new ImmutableCollection<TestDomain>(domain);
            }
            throw new DTOServiceException(CODE);
        }
    }
}
