package kpn.utils;

import java.util.function.Supplier;

import kpn.lib.converter.AbstractEDConverter;
import kpn.lib.converter.EDConverter;

public class EDConvUtils {
    
    public static EDConverter<Long, TestDomain, TestEntity> create(){
        return new TestEDConverter(TestDomain::new, TestEntity::new);
    }

    public static class TestEDConverter extends AbstractEDConverter<Long, TestDomain, TestEntity>{
        public TestEDConverter(Supplier<TestDomain> domainCreator, Supplier<TestEntity> entityCreator) {
            super(domainCreator, entityCreator);
        }
    }
}
