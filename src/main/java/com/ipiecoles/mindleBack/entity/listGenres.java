package com.ipiecoles.mindleBack.entity;

import java.util.Arrays;
import java.util.List;

public class listGenres {

    public List<sousGenres> listGenres ;

    public List<sousGenres> getListGenres() { return listGenres; }

    public void setListGenres(List<sousGenres> listGenres) {
        this.listGenres = listGenres;
    }

    // tous les genres doivent etre en minuscule correspondance avec l'API spotify
    public static final List<String> listMainGenres =
            Arrays.asList(
                    "Jazz",
                    "World-music",
                    "Funk",
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
                    "Reggea",
                    "Punk",
                    "hardcore",
                    "pop",
                    "movies",
                    "latino",
                    "dubstep",
                    "singer-songwriter");
}
