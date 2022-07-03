package kpn.utils;

import java.util.function.Function;

import kpn.lib.services.result.ServiceResult;

public final class TestToResultConverter implements Function<ServiceResult<TestDomain>, TestResult<TestDomain>> {
    
    @Override
    public TestResult<TestDomain> apply(ServiceResult<TestDomain> t) {
        return new TestResult<>(t);
    }
}
