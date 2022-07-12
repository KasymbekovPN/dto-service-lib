package kpn.lib.aspect;

import java.util.Arrays;

import kpn.lib.domain.Domain;
import kpn.lib.executor.ExecutorResult;

public class DefaultAspectResult<D extends Domain<?>> implements AspectResult<D> {
    private boolean success;
    private ExecutorResult<D> executorResult;
    private String code;
    private String[] args;

    public DefaultAspectResult(ExecutorResult<D> collection) {
        this.success = true;
        this.executorResult = collection;
    }

    public DefaultAspectResult(String code, String... args){
        this.success = false;
        this.code = code;
        this.args = args;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public ExecutorResult<D> getExecutorResult() {
        return executorResult;
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
        result = prime * result + ((executorResult == null) ? 0 : executorResult.hashCode());
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
        DefaultAspectResult<D> other = (DefaultAspectResult<D>) obj;
        if (!Arrays.deepEquals(args, other.args))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (executorResult == null) {
            if (other.executorResult != null)
                return false;
        } else if (!executorResult.equals(other.executorResult))
            return false;
        return success == other.success;
    }
}
