package kpn.lib.domain;

import java.util.Queue;

public interface Domain<I> {
    void setId(I id);
    I getId();
    String getInfo();
    String getInDeep(Queue<String> path);
    String getInDeep(String... path);
}
