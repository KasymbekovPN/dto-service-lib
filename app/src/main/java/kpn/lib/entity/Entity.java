package kpn.lib.entity;

public interface Entity<I> {
    void setId(I id);
    I getId();
}
