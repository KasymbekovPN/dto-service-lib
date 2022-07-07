package kpn.lib.id;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DefaultIdObjectTest {

    @Test
    void shoudCheckObjectEquality(){
        assertThat(new DefaultIdObject(new FirstTestClass())).isEqualTo(new DefaultIdObject(new FirstTestClass()));
    }

    @Test
    void shouldCheckObjectInequality(){
        assertThat(new DefaultIdObject(new FirstTestClass())).isNotEqualTo(new DefaultIdObject(new SecondTestClass()));
    }

    @Test
    void shoudCheckHashEquality(){
        assertThat(new DefaultIdObject(new FirstTestClass())).hasSameHashCodeAs(new DefaultIdObject(new FirstTestClass()));
    }

    @Test
    void shouldCheckHashInequality(){
        assertThat(new DefaultIdObject(new FirstTestClass()).hashCode())
            .isNotEqualTo(new DefaultIdObject(new SecondTestClass()).hashCode());
    }

    @Test
    void shouldCheckToString(){
        FirstTestClass instance = new FirstTestClass();
        assertThat(new DefaultIdObject(instance)).hasToString(instance.getClass().getName());
    }

    private static class FirstTestClass {}
    private static class SecondTestClass {}
}
