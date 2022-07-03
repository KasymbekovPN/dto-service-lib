package kpn.lib.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import kpn.lib.collection.Collection;
import kpn.lib.collection.ImmutableCollection;
import kpn.lib.domains.Domain;
import kpn.lib.entities.Entity;

// TODO: del ???
public abstract class AbstractEDConverter<I, D extends Domain<I>, E extends Entity<I>> implements EDConverter<I, D, E> {
    private final Supplier<D> domainCreator;
    private final Supplier<E> entityCreator;
    
    protected AbstractEDConverter(Supplier<D> domainCreator,
                                  Supplier<E> entityCreator) {
        this.domainCreator = domainCreator;
        this.entityCreator = entityCreator;
    }

    @Override
    public D toDomain(E entity) {
        D domain = domainCreator.get();
        domain.setId(entity.getId());
        return domain;
    }

    @Override
    public E toEntity(D domain) {
        E entity = entityCreator.get();
        entity.setId(domain.getId());
        return entity;
    }

    @Override
    public Collection<D> toDomains(Collection<E> entities) {
        ArrayList<E> entityArray = new ArrayList<>();
        entities.iterator().forEachRemaining(entityArray::add);

        List<D> domains = entityArray.stream()
            .map(this::toDomain)
            .collect(Collectors.toList());

        return new ImmutableCollection<>(domains);
    }

    @Override
    public Collection<E> toEntities(Collection<D> domains) {
        ArrayList<D> domainArray = new ArrayList<>();
        domains.iterator().forEachRemaining(domainArray::add);

        List<E> entities = domainArray.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());

        return new ImmutableCollection<>(entities);
    }
}
