package kpn.lib.converter;

import java.util.function.Supplier;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kpn.utils.TestDomain;
import kpn.utils.TestEntity;

public class AbstractToEntityConverterTest {
    
    @Test
    public void shouldCheckConvertion(){
        long id = 1L;
        TestEntity expectedEntity = new TestEntity(id);
        TestEntity entity = new TestConverter(TestEntity::new).convert(new TestDomain(id));

        Assertions.assertThat(entity).isEqualTo(expectedEntity);
    }
    
    private static class TestConverter extends AbstractToEntityConverter<Long, TestDomain, TestEntity>{
        public TestConverter(Supplier<TestEntity> entityCreator) {
            super(entityCreator);
        }
    }
}
