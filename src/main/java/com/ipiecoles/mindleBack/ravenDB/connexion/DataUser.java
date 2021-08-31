package com.ipiecoles.mindleBack.ravenDB.connexion;

import com.ipiecoles.mindleBack.entity.listGenres;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import static com.ipiecoles.mindleBack.ravenDB.connexion.RavenCloudConnexion.*;
import static com.ipiecoles.mindleBack.algorithme.MainAlgorithme.*;
//import static com.ipiecoles.mindleBack.algorithme.RavenManage.GetMainGenres;

public class DataUser {

    public static void main(String[] args) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        GetRavenData("TestALGO", 2);
    }

    public static listGenres GetRavenData (String SpotifyID, Integer Choice) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        listGenres DataRavenUser = ConnexionRaven(SpotifyID);
        if (Choice != 0){
            //AddDataUsers(DataRavenUser,"bossanova", Choice);
            String result = MainMethode(DataRavenUser,"bossanova",Choice,SpotifyID);

            System.out.println(result);
        }
        //String NextGenre = GetMainGenres(DataRavenUser);
        //System.out.println(NextGenre);
        return DataRavenUser;
        }
}
