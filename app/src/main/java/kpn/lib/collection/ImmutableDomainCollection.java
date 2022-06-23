package kpn.lib.collection;

import java.util.Iterator;
import java.util.List;

import kpn.lib.domains.Domain;

public class ImmutableDomainCollection<D extends Domain<?>> implements DomainCollection<D> {
    private final List<D> content;

    protected ImmutableDomainCollection(List<D> content) {
        this.content = content;
    }

    protected ImmutableDomainCollection(D item){
        this.content = List.of(item);
    }

    protected ImmutableDomainCollection(){
        this.content = List.of();
    }

    @Override
    public D getFirst() {
        return content.isEmpty() ? null : content.get(0);
    }

    @Override
    public Iterator<D> iterator() {
        return content.iterator();
    }
}
