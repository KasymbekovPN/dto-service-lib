package kpn.lib.services.result;

import java.util.Arrays;

import kpn.lib.collection.Collection;

// TODO: rename
public class ImmutableServiceResult<D> implements ServiceResult<D> {
    private boolean success;
    private Collection<D> collection;
    private String code;
    private String[] args;

    public ImmutableServiceResult(Collection<D> collection) {
        this.success = true;
        this.collection = collection;
    }

    public ImmutableServiceResult(String code, String... args){
        this.success = false;
        this.code = code;
        this.args = args;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public Collection<D> getCollection() {
        return collection;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(args);
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((collection == null) ? 0 : collection.hashCode());
        result = prime * result + (success ? 1231 : 1237);
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
        ImmutableServiceResult<D> other = (ImmutableServiceResult<D>) obj;
        if (!Arrays.deepEquals(args, other.args))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (collection == null) {
            if (other.collection != null)
                return false;
        } else if (!collection.equals(other.collection))
            return false;
        return success == other.success;
    }
}
