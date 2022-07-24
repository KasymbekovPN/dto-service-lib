package kpn.lib.ripper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import kpn.utils.TestDomain;

class DefaultRipperTest {

    
    @Test
    void shouldCheckRunning_ifPathEmpty(){
        DefaultRipper<TestDomain> ripper = DefaultRipper.<TestDomain>buider().build();
        DefaultRipperArg<TestDomain> arg = new DefaultRipperArg<>(new TestDomain(), new ArrayDeque<>());

        String result = ripper.run(arg);
        assertThat(result).isEqualTo("-");
    }

    @Test
    void shouldCheckRunning_ifGetterAnsent(){
        DefaultRipper<TestDomain> ripper = DefaultRipper.<TestDomain>buider().build();
        ArrayDeque<String> path = new ArrayDeque<String>();
        path.add("id");
        DefaultRipperArg<TestDomain> arg = new DefaultRipperArg<>(new TestDomain(), path);

        String result = ripper.run(arg);
        assertThat(result).isEqualTo("-");

    }

    @Test
    void shouldCheckRunning(){
        String expectedResult = "123";
        DefaultRipper<TestDomain> ripper = DefaultRipper.<TestDomain>buider()
            .getter("id", (arg) -> {
                return Optional.of(expectedResult);
            })
            .build();
        
        ArrayDeque<String> path = new ArrayDeque<String>();
        path.add("id");
        DefaultRipperArg<TestDomain> arg = new DefaultRipperArg<>(new TestDomain(), path);

        String result = ripper.run(arg);
        assertThat(result).isEqualTo(expectedResult);
    }
}
