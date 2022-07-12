package kpn.utils;

import java.util.function.Function;

import kpn.lib.aspect.AspectResult;

public final class TestToResultConverter implements Function<AspectResult<TestDomain>, TestResult<TestDomain>> {
    
    @Override
    public TestResult<TestDomain> apply(AspectResult<TestDomain> t) {
        return new TestResult<>(t);
    }
}
