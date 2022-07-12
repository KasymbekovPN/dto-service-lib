package kpn.lib.domain;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractDomain<I> implements Domain<I> {
    private static final String DEFAULT_GETTING_RESULT = "-";

    private I id;

    @Override
    public void setId(I id) {
        this.id = id;
    }

    @Override
    public I getId() {
        return id;
    }

    @Override
    public String getInfo() {
        return DEFAULT_GETTING_RESULT;
    }

    @Override
    public String getInDeep(Queue<String> path) {
        String key = path.poll();
        if (key != null){
            Map<String, Function<GetterArg<I>, String>> getters = takeGetters();
            if (getters.containsKey(key)){
                return getters.get(key).apply(new GetterArg<>(this, path));
            }
        }
        return DEFAULT_GETTING_RESULT;
    }

    @Override
    public String getInDeep(String... path) {
        return getInDeep(
            new ArrayDeque<>(
                Stream.of(path).collect(Collectors.toList())
            )
        );
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        AbstractDomain<I> other = (AbstractDomain<I>) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    protected Map<String, Function<GetterArg<I>, String>> takeGetters(){
        return Map.of();
    }

    protected static class GetterArg<I> {
        private final Domain<I> domain;
        private final Queue<String> path;

        public GetterArg(Domain<I> domain, Queue<String> path) {
            this.domain = domain;
            this.path = path;
        }
        
        public Domain<I> getDomain() {
            return domain;
        }
        public Queue<String> getPath() {
            return path;
        }       
    }
}