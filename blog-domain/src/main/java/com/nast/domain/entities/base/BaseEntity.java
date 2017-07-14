package com.nast.domain.entities.base;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        try {
            StringBuilder sb = new StringBuilder(getClass().getSimpleName());
            sb.append("{id=").append(id).append(",");
            for (Method method : getClass().getMethods()) {
                if (method.getName().startsWith("get")) {
                    sb.append(StringUtils.uncapitalize(method.getName().split("get")[1]))
                            .append("=")
                            .append(method.invoke(this))
                            .append(",");
                }
            }
            return sb.append("}").toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to print " + getClass());
        }


    }
}
