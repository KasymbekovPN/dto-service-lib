package kpn.lib.converter;

import java.util.function.Supplier;

import kpn.lib.domains.Domain;
import kpn.lib.entities.Entity;

public abstract class AbstractToDomainConverter<I, E extends Entity<I>, D extends Domain<I>> implements ToDomainConverter<I, E, D> {
    private final Supplier<D> domainCreator;
    
    protected AbstractToDomainConverter(Supplier<D> domainCreator) {
        this.domainCreator = domainCreator;
    }

    @Override
    public D convert(E entity) {
        D domain = domainCreator.get();
        domain.setId(entity.getId());
        return domain;
    }
}
