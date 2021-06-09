package com.ipiecoles.mindleBack.entity;

import java.util.List;

public class sousGenres {

    public String name;
    public Integer total;
    public List<String> genre;

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

}
