package com.example.hpc.utils.filtering.criteria.types;

import java.util.List;

public class Filter<Type>{

    private Type equals;
    private Boolean specified;
    private List<Type> in;

    public Type getEquals() {
        return equals;
    }

    public void setEquals(Type equals) {
        this.equals = equals;
    }

    public Boolean getSpecified() {
        return specified;
    }

    public void setSpecified(Boolean specified) {
        this.specified = specified;
    }

    public List<Type> getIn() {
        return in;
    }

    public void setIn(List<Type> in) {
        this.in = in;
    }
}
