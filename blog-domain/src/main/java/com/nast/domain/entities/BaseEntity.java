package com.nast.domain.entities;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity implements Persistable {

    private Long id;

    @Transient
    @Override
    public boolean isPersisted() {
        return this.id != null && this.id != 0;
    }

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false)
    @Override
    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass() && id.equals(((BaseEntity) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
