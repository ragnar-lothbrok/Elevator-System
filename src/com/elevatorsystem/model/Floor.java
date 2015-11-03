package com.elevatorsystem.model;

import java.io.Serializable;

public class Floor implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer position;
    private Boolean isDestination;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getIsDestination() {
        return isDestination;
    }

    public void setIsDestination(Boolean isDestination) {
        this.isDestination = isDestination;
    }

    public Floor(Integer position) {
        super();
        this.position = position;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Floor other = (Floor) obj;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Floor [position=" + position + ", isDestination=" + isDestination + "]";
    }

}
