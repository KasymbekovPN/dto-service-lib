package kpn.lib.converter;

import kpn.lib.domains.Domain;
import kpn.lib.entities.Entity;

@FunctionalInterface
public interface ToDomainConverter<I, E extends Entity<I>, D extends Domain<I>> {
    D convert(E entity);
}
