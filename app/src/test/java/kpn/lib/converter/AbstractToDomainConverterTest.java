package kpn.lib.converter;

import java.util.function.Supplier;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.utils.TestDomain;
import kpn.utils.TestEntity;

public class AbstractToDomainConverterTest {
    
    @Test
    public void shouldCheckConvertion(){
        long id = 1L;
        TestDomain expectedDomain = new TestDomain(id);
        TestDomain domain = new TestConverter(TestDomain::new).convert(new TestEntity(id));

        Assertions.assertThat(domain).isEqualTo(expectedDomain);
    }

    private static class TestConverter extends AbstractToDomainConverter<Long, TestEntity, TestDomain>{
        public TestConverter(Supplier<TestDomain> domainCreator) {
            super(domainCreator);
        }
    }
}
