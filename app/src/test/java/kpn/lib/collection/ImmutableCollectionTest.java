package kpn.lib.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import kpn.lib.domains.AbstractDomain;

class ImmutableCollectionTest {

    private static final long ID = 1L;
    
    @Test
    void shouldCheckGetFirst_whenItemAbsent(){
        assertThat(new ImmutableCollection<TestDomain>().getFirst()).isNull();
    }

    @Test
    void shouldCheckGetFirst(){
        assertThat(createCollection(ID).getFirst()).isEqualTo(creteDomain(ID));
    }

    @Test
    void shouldCheckIterator_whenItemsAbsent(){
        Iterator<TestDomain> iterator = new ImmutableCollection<TestDomain>().iterator();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void shouldCheckIterator(){
        Long[] ids = new Long[]{ID, ID + 1, ID + 2};
        List<TestDomain> expectedDomains = createDomains(ids);

        ArrayList<TestDomain> domains = new ArrayList<TestDomain>();
        Iterator<TestDomain> iterator = createCollection(ids).iterator();
        iterator.forEachRemaining(domains::add);

        assertThat(domains).isEqualTo(expectedDomains);
    }

    private ImmutableCollection<TestDomain> createCollection(Long... ids){
        return new ImmutableCollection<>(createDomains(ids));
    }

    private List<TestDomain> createDomains(Long... ids){
        return Stream.of(ids)
            .map(this::creteDomain)
            .collect(Collectors.toList());
    }

    private TestDomain creteDomain(long id) {
        TestDomain domain = new TestDomain();
        domain.setId(id);
        return domain;
    }

    private static class TestDomain extends AbstractDomain<Long>{}
}
