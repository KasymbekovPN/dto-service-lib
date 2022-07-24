package kpn.lib.ripper;

import java.util.Queue;

import kpn.lib.domain.Domain;

public interface RipperArg<D extends Domain<?>> {
    D getDomain();
    Queue<String> getPath();
}
