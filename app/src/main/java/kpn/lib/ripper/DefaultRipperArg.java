package kpn.lib.ripper;

import java.util.Queue;

import kpn.lib.domain.Domain;

public class DefaultRipperArg<D extends Domain<?>> implements RipperArg<D>{
    private final D domain;
    private final Queue<String> path;
    
    public DefaultRipperArg(D domain, Queue<String> path) {
        this.domain = domain;
        this.path = path;
    }

    @Override
    public D getDomain() {
        return domain;
    }

    @Override
    public Queue<String> getPath() {
        return path;
    }
}
