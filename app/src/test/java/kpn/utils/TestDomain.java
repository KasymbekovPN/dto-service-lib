package kpn.utils;

import kpn.lib.domain.AbstractDomain;

public class TestDomain extends AbstractDomain<Long> {
    
    public TestDomain() {
    }

    public TestDomain(long id) {
        setId(id);
    }
}
