package kpn.lib.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AbstractEntityTest {
    
    private static final Long FIRST_ID = 1L;
    private static final Long SECOND_ID = 2L;

    @Test
    void shouldCheckEqualily(){
        assertThat(createEntity(FIRST_ID)).isEqualTo(createEntity(FIRST_ID));
    }

    @Test
    void shouldCheckInequalily(){
        assertThat(createEntity(FIRST_ID)).isNotEqualTo(createEntity(SECOND_ID));
    }

    @Test
    void shouldCheckHashCodes_equalityObects(){
        assertThat(createEntity(FIRST_ID)).hasSameHashCodeAs(createEntity(FIRST_ID));
    }

    @Test
    void shouldCheckHashCodes_inequalityObects(){
        assertThat(createEntity(FIRST_ID).hashCode()).isNotEqualTo(createEntity(SECOND_ID).hashCode());
    }

    private TestEntity createEntity(Long id) {
        TestEntity entity = new TestEntity();
        entity.setId(id);
        return entity;
    }

    private static class TestEntity extends AbstractEntity<Long>{
        private Long id;

        @Override
        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public Long getId() {
            return id;
        }
    }
}
