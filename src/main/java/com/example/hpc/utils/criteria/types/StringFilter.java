package com.example.hpc.utils.criteria.types;


import com.example.hpc.utils.criteria.types.Filter;

public class StringFilter extends Filter<String> {

    private String contains;
    private String startWith;

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public String getStartWith() {
        return startWith;
    }

    public void setStartWith(String startWith) {
        this.startWith = startWith;
    }
}
