package kpn.lib.converter;

import kpn.lib.collection.Collection;
import kpn.lib.domains.Domain;
import kpn.lib.entities.Entity;

public interface EDConverter<I, D extends Domain<I>, E extends Entity<I>> {
    D toDomain(E entity);
    E toEntity(D domain);
    Collection<D> toDomains(Collection<E> entities);
    Collection<E> toEntities(Collection<D> domains);
}
