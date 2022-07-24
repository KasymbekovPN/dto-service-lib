package kpn.lib.domain;

public interface Domain<I> {
    void setId(I id);
    I getId();
    String getInfo();
}
