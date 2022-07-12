package kpn.utils;

import java.util.Arrays;

import kpn.lib.aspect.AspectResult;
import kpn.lib.domain.Domain;
import kpn.lib.executor.ExecutorResult;

public final class TestResult<D extends Domain<?>> {
    private final boolean success;
    private final ExecutorResult<D> collection;
    private final String code;
    private final String[] args;

    public TestResult(AspectResult<D> result) {
        this.success = result.isSuccess();
        this.collection = result.getExecutorResult();
        this.code = result.getCode();
        this.args = result.getArgs();
    }

    public TestResult(ExecutorResult<D> collection){
        this.success = true;
        this.collection = collection;
        this.code = null;
        this.args = null;
    }

    public TestResult(String code, String... args){
        this.success = false;
        this.collection = null;
        this.code = code;
        this.args = args;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(args);
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
        TestResult<D> other = (TestResult<D>) obj;
        if (!Arrays.equals(args, other.args))
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
        if (success != other.success)
            return false;
        return true;
    }
}
