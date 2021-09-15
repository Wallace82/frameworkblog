package com.frameworkdigital.blog.domain;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseEntity <T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;

    public abstract T getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

