package kpn.utils;

import kpn.lib.entity.AbstractEntity;

public class TestEntity extends AbstractEntity<Long>{
    private Long id;

    public TestEntity() {
    }

    public TestEntity(Long id) {
        setId(id);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
