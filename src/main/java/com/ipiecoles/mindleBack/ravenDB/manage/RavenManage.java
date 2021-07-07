package com.ipiecoles.mindleBack.ravenDB.manage;

import com.ipiecoles.mindleBack.entity.listGenres;
import com.ipiecoles.mindleBack.entity.sousGenres;

import static com.ipiecoles.mindleBack.entity.listGenres.listMainGenres;
import static com.ipiecoles.mindleBack.ravenDB.connexion.RavenCloudConnexion.session;

import java.util.ArrayList;
import java.util.List;

public class RavenManage {

    public static void AddDataUsers(listGenres Data, String Genres,Integer Choice){
        List<String> mainGenres = null;
        for (sousGenres sousGenre: Data.listGenres) {
            if(sousGenre.name.equals(Genres)){
                //System.out.println("oui");
                switch(Choice) {                // Provisoire
                    case 1:
                        sousGenre.total = sousGenre.total + 1;
                        break;
                    case 2:
                        sousGenre.total = sousGenre.total - 1;
                        break;
                    default:
                        continue;
                }
                mainGenres = sousGenre.genre;
                //System.out.println(mainGenres);
            }
        }
        if (mainGenres.size() != 0 || mainGenres == null){
            for (sousGenres sousGenre: Data.listGenres) {
                for (String mainGenre : mainGenres){
                    if (mainGenre.equals(sousGenre.name)){
                        switch(Choice) {                        // Provisoire
                            case 1:
                                sousGenre.total = sousGenre.total + 1;
                                break;
                            case 2:
                                sousGenre.total = sousGenre.total - 1;
                                break;
                            default:
                                continue;
                        }
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
                if (sousGenre.name.equals(MainGenre)){
                    ///////////////PROVISOIRE//////////////////
                    if (sousGenre.total >= SizeDemo) {
                        MainGenreDemo = sousGenre.name;
                        SizeDemo = sousGenre.total;
                    }
                    ///////////////PROVISOIRE//////////////////
                    ArrayList<String> Provisoire = new ArrayList();
                    Provisoire.add(sousGenre.name);
                    Provisoire.add(sousGenre.total.toString());
                    Totals.add(Provisoire);
                }
            }
        }
        if (MainGenreDemo.equals("") || SizeDemo.equals(0)){
            MainGenreDemo =  "World-Music";
        }
        //System.out.println(MainGenreDemo);
        //System.out.println(Totals);

        return MainGenreDemo;
    }

}
