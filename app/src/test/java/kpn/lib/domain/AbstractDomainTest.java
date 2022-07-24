package kpn.lib.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AbstractDomainTest {
    
    private static final long ID_AS_LONG = 123L;

    @Test
    void shouldCheckDefaultGetting(){
        assertThat(new TestDomain().getId()).isNull();
    }

    @Test
    void shouldCheckSettingGetting(){
        TestDomain domain = createDomain(ID_AS_LONG);

        assertThat(domain.getId()).isEqualTo(ID_AS_LONG);
    }

    @Test
    void shouldCheckEqualily(){
        assertThat(createDomain(ID_AS_LONG)).isEqualTo(createDomain(ID_AS_LONG));
    }

    @Test
    void shouldCheckInequality(){
        assertThat(createDomain(ID_AS_LONG)).isNotEqualTo(createDomain(ID_AS_LONG + 1));
    }

    @Test
    void shouldCheckHashCodes_equlityObjects(){
        assertThat(createDomain(ID_AS_LONG)).hasSameHashCodeAs(createDomain(ID_AS_LONG));
    }

    @Test
    void shouldCheckHashCodes_inequlityObjects(){
        assertThat(createDomain(ID_AS_LONG).hashCode()).isNotEqualTo(createDomain(ID_AS_LONG + 1).hashCode());
    }

    private TestDomain createDomain(long id){
        TestDomain domain = new TestDomain();
        domain.setId(id);
        return domain;
    }

    private static class TestDomain extends AbstractDomain<Long>{}
}
