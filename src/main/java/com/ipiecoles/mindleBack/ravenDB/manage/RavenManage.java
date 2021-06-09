package com.ipiecoles.mindleBack.ravenDB.manage;

import com.ipiecoles.mindleBack.entity.listGenres;
import com.ipiecoles.mindleBack.entity.sousGenres;

import static com.ipiecoles.mindleBack.entity.listGenres.listMainGenres;
import static com.ipiecoles.mindleBack.ravenDB.connexion.RavenCloudConnexion.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RavenManage {

    public static void AddDataUsers(listGenres Data, String Genres,Integer Choice){
        List<String> mainGenres = null;
        for (sousGenres sousGenre: Data.listGenres) {
            if(sousGenre.Name.equals(Genres)){
                System.out.println("oui");
                switch(Choice) {                // Provisoire
                    case 1:
                        sousGenre.Total = sousGenre.Total + 1;
                        break;
                    case 2:
                        sousGenre.Total = sousGenre.Total - 1;
                        break;
                    default:
                        continue;
                }
                mainGenres = sousGenre.Genre;
                System.out.println(mainGenres);
            }
        }
        if (mainGenres.size() != 0){
            for (sousGenres sousGenre: Data.listGenres) {
                for (String mainGenre : mainGenres){
                    if (mainGenre.equals(sousGenre.Name)){
                        switch(Choice) {                        // Provisoire
                            case 1:
                                sousGenre.Total = sousGenre.Total + 1;
                                break;
                            case 2:
                                sousGenre.Total = sousGenre.Total - 1;
                                break;
                            default:
                                continue;
                        }
                        sousGenre.Total = sousGenre.Total + 1;
                    }
                }
            }
        }
        session.saveChanges();
    }

    public static String GetMainGenres(listGenres Data){   //Méthode provisoir en attente de l'algorithme
        ArrayList<ArrayList<String>> Totals = new ArrayList();
        String MainGenreDemo = "";
        Integer SizeDemo = 0;
        for (String MainGenre : listMainGenres) {
            for (sousGenres sousGenre: Data.listGenres) {
                if (sousGenre.Name.equals(MainGenre)){
                    ///////////////PROVISOIRE//////////////////
                    if (sousGenre.Total >= SizeDemo) { MainGenreDemo = sousGenre.Name; }
                    ///////////////PROVISOIRE//////////////////
                    ArrayList<String> Provisoire = new ArrayList();
                    Provisoire.add(sousGenre.Name);
                    Provisoire.add(sousGenre.Total.toString());
                    Totals.add(Provisoire);
                }
            }
        }
        if (MainGenreDemo.equals("")){
            MainGenreDemo =  "World-Music";
        }
        System.out.println(MainGenreDemo);
        return MainGenreDemo;
    }

}
