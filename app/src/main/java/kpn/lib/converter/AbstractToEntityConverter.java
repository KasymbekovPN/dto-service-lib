package kpn.lib.converter;

import java.util.function.Supplier;

import kpn.lib.domains.Domain;
import kpn.lib.entities.Entity;

public abstract class AbstractToEntityConverter<I, D extends Domain<I>, E extends Entity<I>> implements ToEntityConverter<I,D,E> {
    private final Supplier<E> entityCreator;

    protected AbstractToEntityConverter(Supplier<E> entityCreator) {
        this.entityCreator = entityCreator;
    }

    @Override
    public E convert(D domain) {
        E entity = entityCreator.get();
        entity.setId(domain.getId());
        return entity;
    }
}
