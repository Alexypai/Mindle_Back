package com.ipiecoles.mindleBack.algorithme;

import com.ipiecoles.mindleBack.entity.listGenres;
import com.ipiecoles.mindleBack.entity.sousGenres;

import static com.ipiecoles.mindleBack.algorithme.methodeForAlgorithme.ApplyData;
import static com.ipiecoles.mindleBack.entity.listGenres.listMainGenres;
import static com.ipiecoles.mindleBack.ravenDB.connexion.RavenCloudConnexion.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainAlgorithme {

    /**
     * Méthode permettant d'enregistrer le choix effectuer par l'utilisateur en base de données
     *
     * @param DataUser Correspond au document NoSQL de l'utilisateur
     * @param Genres   Le Genre qui viens d'etre jouer a l'utilsiateur
     * @param Choice   Le Choix (aime, n'aime pas) concernant le genre effectuer par l'utilisateur
     */
    public static void AddDataUsers(listGenres DataUser, String Genres, Integer Choice) {
        List<String> mainGenres = Collections.emptyList();
        boolean OneMainGenre = false;
        for (String CheckMain : listMainGenres) {
            if (CheckMain.equals(Genres)) {
                OneMainGenre = true;
                mainGenres = Collections.singletonList(Genres);
                System.out.println("Ok");
            }
        }
        if (!OneMainGenre) {
            for (sousGenres sousGenre : DataUser.listGenres) {
                if (sousGenre.name.equals(Genres)) {
                    sousGenre = ApplyData(Choice, sousGenre, 1);
                    mainGenres = sousGenre.genre;
                    System.out.println(mainGenres);
                }
            }
        }
        if (mainGenres.size() != 0 || OneMainGenre) {
            for (sousGenres mainGenreUser : DataUser.listGenres) {
                for (String mainGenre : mainGenres) {
                    if (mainGenre.equals(mainGenreUser.name)) {
                        mainGenreUser = ApplyData(Choice, mainGenreUser, 2);
                    }
                }
            }
        }
        session.saveChanges();
    }

    /**
     * Méthode permettant de connaitre les gouts de l'utilisateur par rapport aux genres principaux
     *
     * @param DataUser Correspond au document NoSQL de l'utilisateur
     */

    public static Integer GetRandomChoice(listGenres DataUser) {
        ArrayList<ArrayList<String>> Totals = new ArrayList();
        boolean nullableGenre = false;
        boolean superLike = false;
        Integer dislike = 0;
        Integer randomChoice = null;
        Integer like = 0;
        for (String MainGenre : listMainGenres) {
            for (sousGenres sousGenre : DataUser.listGenres) {
                if (sousGenre.name.equals(MainGenre)) {
                    ArrayList<String> Provisoire = new ArrayList();
                    Provisoire.add(sousGenre.name);
                    Provisoire.add(sousGenre.total.toString());
                    if (sousGenre.total.equals(0)) {
                        nullableGenre = true;
                    }
                    if (sousGenre.total > 0) {
                        like = like + 1;
                    }
                    if (sousGenre.total >= 150) {
                        superLike = true;
                    }
                    if (sousGenre.total < 0) {
                        dislike = dislike + 1;
                    }
                    Totals.add(Provisoire);
                }
            }
        }
        if (nullableGenre) {
            randomChoice = 0;
        } else {
            randomChoice = 1;
            if (like > 3) {
                randomChoice = 2;
            }
            if (superLike) {
                randomChoice = 3;
            }
            if (dislike.equals(22)) {
                randomChoice = 4;
            }
            if (like.equals(22)) {
                randomChoice = 5;
            }

        }

        System.out.println(Totals);
        System.out.println("Rand choice : " + randomChoice);

        return randomChoice;
    }

    public static String RandomFunction(listGenres DataUser) {
        String result = null;
        Double rand = Math.random();
        Double pourcentRand = rand * 100;
        Integer choicebyrandom = null;
        Integer randomChoice = GetRandomChoice(DataUser);
        System.out.println(pourcentRand);
        switch (randomChoice) {
            case 0:
                result = GetGenreNeverSelected(DataUser);
                break;
            case 1:
                if (pourcentRand >= 50) {
                    result = GetGenreByRandomChoice(DataUser,true);
                    System.out.println("positif : " + pourcentRand);
                } else {
                    result = GetGenreByRandomChoice(DataUser,false);
                    System.out.println("negatif : " + pourcentRand);
                }
                break;
            case 2:
                if (pourcentRand >= 30) {
                    result = GetGenreByRandomChoice(DataUser,true);
                    System.out.println("positif : " + pourcentRand);
                } else {
                    result = GetGenreByRandomChoice(DataUser,false);
                    System.out.println("negatif : " + pourcentRand);
                }
                break;
            case 3:
                if (pourcentRand <= 35) {
                    result = GetGenreByRandomChoice(DataUser,true);
                    System.out.println("positif : " + pourcentRand);
                } else if (pourcentRand > 35 && pourcentRand < 50) {
                    result = GetGenreByRandomChoice(DataUser,false);
                    System.out.println("negatif : " + pourcentRand);
                }else if (pourcentRand >= 50){
                    result = GetBestGenre(DataUser);
                }
                break;
            case 4:
                result = GetGenreByDefault(DataUser,false);
                break;
            case 5:
                result = GetGenreByDefault(DataUser,true);
                break;
        }
        return result;
    }

    public static String GetGenreNeverSelected(listGenres DataUser) {
        String result = null;
        for (String MainGenre : listMainGenres) {
            for (sousGenres sousGenre : DataUser.listGenres) {
                if (sousGenre.name.equals(MainGenre) && sousGenre.total.equals(0)) {
                    result = sousGenre.name;
                    break;
                }
            }
            if (result != null) {
                break;
            }
        }
        return result;
    }

    public static String GetBestGenre(listGenres DataUser) {
        ArrayList<ArrayList<String>> Totals = new ArrayList();
        ArrayList result = new ArrayList();
        String finalResult = null;
        Integer intMax = 0;
        Integer intMin = 0;
        for (String MainGenre : listMainGenres) {
            for (sousGenres sousGenre : DataUser.listGenres) {
                if (sousGenre.name.equals(MainGenre) && sousGenre.total >= 150) {
                    ArrayList<String> Provisoire = new ArrayList();
                    Provisoire.add(sousGenre.name);
                    Provisoire.add(sousGenre.total.toString());

                    System.out.println("Name selected : " + sousGenre.name);

                    Totals.add(Provisoire);
                    intMax = intMax + 1;
                }
            }
        }
        Random random = new Random();
        if (intMax > 0) {
            Integer value = random.nextInt(intMax + intMin) + intMin;
            System.out.println("value rand " + value);
            result = Totals.get(value);
            finalResult = (String) result.get(0);
        }else{
                finalResult = GetGenreByDefault(DataUser,true);
            if (finalResult == null){
                finalResult = GetGenreByDefault(DataUser,false);
            }
        }
        System.out.println("final : " + finalResult);

        return finalResult;
    }

    public static String GetGenreByDefault(listGenres DataUser, boolean goodOrNot) {
        String result = null;
        Integer BestGenre = null;
        if (goodOrNot){
            BestGenre = 0;
        }else{
            BestGenre = -1000;
        }
        for (String MainGenre : listMainGenres) {
            for (sousGenres sousGenre : DataUser.listGenres) {
                if (sousGenre.name.equals(MainGenre)) {
                    if (BestGenre < sousGenre.total) {
                        result = sousGenre.name;
                        BestGenre = sousGenre.total;
                    }
                }
            }
        }
        return result;
    }

    public static String GetGenreByRandomChoice(listGenres DataUser, boolean goodOrNot) {
        ArrayList<ArrayList<String>> Totals = new ArrayList();
        ArrayList result = new ArrayList();
        String finalResult = null;
        Integer intMax = 0;
        Integer intMin = 0;
        for (String MainGenre : listMainGenres) {
            for (sousGenres sousGenre : DataUser.listGenres) {
                if (sousGenre.name.equals(MainGenre) && ((sousGenre.total > 0 && goodOrNot) || (sousGenre.total < 0 && !goodOrNot))) {
                    ArrayList<String> Provisoire = new ArrayList();
                    Provisoire.add(sousGenre.name);
                    Provisoire.add(sousGenre.total.toString());

                    System.out.println("Name selected : " + sousGenre.name);

                    Totals.add(Provisoire);
                    intMax = intMax + 1;
                }
            }
        }
        Random random = new Random();
        if (intMax > 0) {
            Integer value = random.nextInt(intMax + intMin) + intMin;
            System.out.println("value rand " + value);
            result = Totals.get(value);
            finalResult = (String) result.get(0);
        }else{
            if (goodOrNot){
                finalResult = GetGenreByDefault(DataUser,false);
            }else{
                finalResult = GetGenreByDefault(DataUser,true);
            }
        }
        System.out.println("final : " + finalResult);

        return finalResult;
    }


}

