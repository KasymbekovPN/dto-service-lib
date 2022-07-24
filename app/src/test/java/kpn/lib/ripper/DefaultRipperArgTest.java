package kpn.lib.ripper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import kpn.utils.TestDomain;

class DefaultRipperArgTest {
    
    @Test
    void shouldCheckDomainGetting(){
        TestDomain domain = new DefaultRipperArg<>(new TestDomain(1L), null).getDomain();
        assertThat(domain).isEqualTo(new TestDomain(1L));
    }

    @Test
    void shouldCheckPathGetting(){
        ArrayDeque<String> expectedPath = new ArrayDeque<>();
        expectedPath.add("item");
        Queue<String> path = new DefaultRipperArg<>(null, expectedPath).getPath();

        assertThat(path).isEqualTo(expectedPath);
    }
}
