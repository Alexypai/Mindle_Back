package com.ipiecoles.mindleBack.entity;

import java.util.Arrays;
import java.util.List;


public class listGenres {

    public List<sousGenres> listGenres ;

    public List<sousGenres> getListGenres() { return listGenres; }

    public void setListGenres(List<sousGenres> listGenres) {
        this.listGenres = listGenres;
    }

    public static final List<String> listMainGenres =
            Arrays.asList(
                    "jazz",
                    "world-music",
                    "funk",
                    "alternative",
                    "ambient",
                    "metal",
                    "blues",
                    "country",
                    "electro",
                    "hip-hop",
                    "classical",
                    "house",
                    "techno",
                    "disco",
                    "reggea",
                    "punk",
                    "hardcore",
                    "pop",
                    "movies",
                    "latino",
                    "dubstep",
                    "singer-songwriter");
}


