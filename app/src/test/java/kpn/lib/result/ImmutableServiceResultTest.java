package kpn.lib.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import kpn.lib.collection.ImmutableCollection;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.utils.TestDomain;

class ImmutableServiceResultTest {
    
    private static final String CODE = "code";
    private static final String[] ARGS = new String[]{"1", "2", "3"};

    @Test
    void shouldCheckIsSuccess_ifFalse(){
        assertThat(new ImmutableServiceResult<TestDomain>(CODE).isSuccess()).isFalse();
    }

    @Test
    void shouldCheckIsSuccess_ifTrue(){
        assertThat(new ImmutableServiceResult<>(new ImmutableCollection<>()).isSuccess()).isTrue();
    }

    @Test
    void shouldCheckCollection_ifAbsent(){
        assertThat(new ImmutableServiceResult<TestDomain>(CODE).getCollection()).isNull();
    }

    @Test
    void shouldCheckCollection(){
        assertThat(new ImmutableServiceResult<>(new ImmutableCollection<>(new TestDomain(1L))).getCollection())
            .isEqualTo(new ImmutableCollection<>(new TestDomain(1L)));
    }

    @Test
    void shouldCheckCode_ifAnsent(){
        assertThat(new ImmutableServiceResult<>(new ImmutableCollection<>(new TestDomain(1L))).getCode()).isNull();
    }

    @Test
    void shouldCheckCode(){
        assertThat(new ImmutableServiceResult<TestDomain>(CODE).getCode()).isEqualTo(CODE);
    }

    @Test
    void shouldCheckArgs_ifAnsent(){
        assertThat(new ImmutableServiceResult<>(new ImmutableCollection<>(new TestDomain(1L))).getArgs()).isNull();
    }

    @Test
    void shouldCheckArgs(){
        assertThat(new ImmutableServiceResult<TestDomain>(CODE, ARGS).getArgs()).isEqualTo(ARGS);
    }
}
