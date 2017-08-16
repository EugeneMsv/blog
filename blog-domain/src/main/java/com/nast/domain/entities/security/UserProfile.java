package com.nast.domain.entities.security;

import com.nast.domain.entities.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false, unique = true)
    private UserProfileType type;

    public UserProfileType getType() {
        return type;
    }

    public void setType(UserProfileType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserProfile))
            return false;
        UserProfile other = (UserProfile) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserProfile [id=" + id + ", type=" + type + "]";
    }


}
