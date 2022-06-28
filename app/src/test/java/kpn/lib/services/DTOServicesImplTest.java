package kpn.lib.services;

import org.junit.jupiter.api.Test;

import kpn.utils.TestDomain;

public class DTOServicesImplTest {
    
    @Test
    void doSth(){
        DTOServicesImpl<Long, TestDomain, Integer, String> service = new DTOServicesImpl<>();

        String all = service.loader().all();
    }
}
