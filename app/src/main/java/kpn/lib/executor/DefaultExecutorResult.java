package kpn.lib.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import kpn.lib.domain.Domain;

public class DefaultExecutorResult<D extends Domain<?>> implements ExecutorResult<D> {
    private final List<D> content;

    public DefaultExecutorResult(List<D> content) {
        this.content = Collections.unmodifiableList(content);
    }

    public DefaultExecutorResult(D item){
        List<D> list = new ArrayList<>();
        list.add(item);
        this.content = Collections.unmodifiableList(list);
    }

    public DefaultExecutorResult(){
        this.content = Collections.unmodifiableList(new ArrayList<>());
    }

    @Override
    public D getFirst() {
        return content.isEmpty() ? null : content.get(0);
    }

    @Override
    public Iterator<D> iterator() {
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
        DefaultExecutorResult<D> other = (DefaultExecutorResult<D>) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        return true;
    }
}
