package kpn.lib.aspect;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import kpn.lib.executor.DefaultExecutorResult;
import kpn.utils.TestDomain;

class DefaultAspectResultTest {
    
    private static final String CODE = "code";
    private static final String[] ARGS = new String[]{"1", "2", "3"};

    @Test
    void shouldCheckIsSuccess_ifFalse(){
        assertThat(new DefaultAspectResult<TestDomain>(CODE).isSuccess()).isFalse();
    }

    @Test
    void shouldCheckIsSuccess_ifTrue(){
        assertThat(new DefaultAspectResult<>(new DefaultExecutorResult<>()).isSuccess()).isTrue();
    }

    @Test
    void shouldCheckCollection_ifAbsent(){
        assertThat(new DefaultAspectResult<TestDomain>(CODE).getExecutorResult()).isNull();
    }

    @Test
    void shouldCheckCollection(){
        assertThat(new DefaultAspectResult<>(new DefaultExecutorResult<>(new TestDomain(1L))).getExecutorResult())
            .isEqualTo(new DefaultExecutorResult<>(new TestDomain(1L)));
    }

    @Test
    void shouldCheckCode_ifAnsent(){
        assertThat(new DefaultAspectResult<>(new DefaultExecutorResult<>(new TestDomain(1L))).getCode()).isNull();
    }

    @Test
    void shouldCheckCode(){
        assertThat(new DefaultAspectResult<TestDomain>(CODE).getCode()).isEqualTo(CODE);
    }

    @Test
    void shouldCheckArgs_ifAnsent(){
        assertThat(new DefaultAspectResult<>(new DefaultExecutorResult<>(new TestDomain(1L))).getArgs()).isNull();
    }

    @Test
    void shouldCheckArgs(){
        assertThat(new DefaultAspectResult<TestDomain>(CODE, ARGS).getArgs()).isEqualTo(ARGS);
    }
}
