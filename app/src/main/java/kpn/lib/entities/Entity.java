package kpn.lib.entities;

public interface Entity<I> {
    void setId(I id);
    I getId();
}
