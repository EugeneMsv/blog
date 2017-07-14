package com.nast.domain.entities.base;

import java.io.Serializable;

public interface Persistable extends Serializable {

    boolean isPersisted();

    Long getId();
}
