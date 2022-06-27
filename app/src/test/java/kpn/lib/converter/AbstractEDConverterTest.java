package kpn.lib.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import kpn.lib.collection.Collection;
import kpn.lib.collection.ImmutableCollection;
import kpn.utils.TestDomain;
import kpn.utils.TestEntity;

class AbstractEDConverterTest {
    private static final long ID = 1L;

    @Test
    void shouldCheckToDomainConvertion(){
        TestConverter converter = new TestConverter(TestDomain::new, TestEntity::new);
        TestDomain domain = converter.toDomain(new TestEntity(ID));

        assertThat(domain).isEqualTo(new TestDomain(ID));
    }

    @Test
    void shouldCheckToEntityConvertion(){
        TestConverter converter = new TestConverter(TestDomain::new, TestEntity::new);
        TestEntity entity = converter.toEntity(new TestDomain(ID));

        assertThat(entity).isEqualTo(new TestEntity(ID));
    }

    @Test
    void shouldCheckToDomainsConvertion(){
        TestConverter converter = new TestConverter(TestDomain::new, TestEntity::new);
        Collection<TestDomain> domains = converter.toDomains(new ImmutableCollection<TestEntity>(new TestEntity(ID)));

        assertThat(domains).isEqualTo(new ImmutableCollection<>(new TestDomain(ID)));
    }

    @Test
    void shouldCheckToEntitiesConvertion(){
        TestConverter converter = new TestConverter(TestDomain::new, TestEntity::new);
        Collection<TestEntity> entities = converter.toEntities(new ImmutableCollection<>(new TestDomain(ID)));

        assertThat(entities).isEqualTo(new ImmutableCollection<>(new TestEntity(ID)));
    }

    private static class TestConverter extends AbstractEDConverter<Long, TestDomain, TestEntity>{
        public TestConverter(Supplier<TestDomain> domainCreator, Supplier<TestEntity> entityCreator) {
            super(domainCreator, entityCreator);
        }
    }
}
