package kpn.lib.converter;

import kpn.lib.domains.Domain;
import kpn.lib.entities.Entity;

public interface ToEntityConverter<I, D extends Domain<I>, E extends Entity<I>> {
    E convert(D domain);    
}
