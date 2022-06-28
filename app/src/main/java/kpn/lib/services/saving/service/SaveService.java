package kpn.lib.services.saving.service;

import kpn.lib.domains.Domain;

public interface SaveService<I, D extends Domain<I>, R> {
    R save(D domain);
}
