package kpn.lib.collection;

import java.util.Iterator;
import java.util.List;

public class ImmutableCollection<T> implements Collection<T> {
    private final List<T> content;

    public ImmutableCollection(List<T> content) {
        this.content = content;
    }

    public ImmutableCollection(T item){
        this.content = List.of(item);
    }

    public ImmutableCollection(){
        this.content = List.of();
    }

    @Override
    public T getFirst() {
        return content.isEmpty() ? null : content.get(0);
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ImmutableCollection<T> other = (ImmutableCollection<T>) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        return true;
    }
}
