package com.nast.domain.entities.base;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity implements Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @Override
    public boolean isPersisted() {
        return this.id != null && this.id != 0;
    }


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

    @Override
    public String toString() {
        StringBuilder sb =  new StringBuilder(getClass().getSimpleName());
        return sb.append("{id=").append(id).append("}").toString();
    }
}