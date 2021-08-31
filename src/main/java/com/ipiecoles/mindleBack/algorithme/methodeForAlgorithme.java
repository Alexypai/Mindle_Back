package com.ipiecoles.mindleBack.algorithme;

import com.ipiecoles.mindleBack.entity.sousGenres;

public class methodeForAlgorithme {

public static sousGenres ApplyData (Integer Choice, sousGenres UpdateGenreUser, Integer Switch) {
        switch (Choice) {
            case 1:
                UpdateGenreUser.total = UpdateGenreUser.total - 1*Switch;
                System.out.println("Genre "+ -1*Switch + " " + UpdateGenreUser.name);
                break;
            case 2:
                UpdateGenreUser.total = UpdateGenreUser.total + 2*Switch;
                System.out.println("Genre +"+ 2*Switch + " " + UpdateGenreUser.name);
                break;
            case 3:
                UpdateGenreUser.total = UpdateGenreUser.total + 3*Switch;
                System.out.println("Genre +"+ 3*Switch + " " + UpdateGenreUser.name);
                break;
            default:
                break;
        }
        return UpdateGenreUser;
    }
}

