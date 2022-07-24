package kpn.lib.ripper;

import kpn.lib.domain.Domain;

public interface Ripper<D extends Domain<?>> {
    String run(RipperArg<D> arg);
}
