package kpn.utils;

import kpn.lib.domains.AbstractDomain;

public class TestDomain extends AbstractDomain<Long> {
    public TestDomain(long id) {
        setId(id);
    }
}
