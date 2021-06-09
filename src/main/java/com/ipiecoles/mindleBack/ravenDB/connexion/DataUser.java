package com.ipiecoles.mindleBack.ravenDB.connexion;

import com.ipiecoles.mindleBack.entity.listGenres;
import net.ravendb.client.documents.session.IDocumentSession;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.zip.ZipEntry;

import static com.ipiecoles.mindleBack.ravenDB.connexion.RavenCloudConnexion.*;
import static com.ipiecoles.mindleBack.ravenDB.manage.RavenManage.AddDataUsers;
import static com.ipiecoles.mindleBack.ravenDB.manage.RavenManage.GetMainGenres;

public class DataUser {

    public static void main(String[] args) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        GetRavenData("UserTest", 2);
    }

    public static listGenres GetRavenData (String SpotifyID, Integer Choice) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        listGenres DataRavenUser = ConnexionRaven(SpotifyID);
        if (Choice != 0){
            AddDataUsers(DataRavenUser,"Afrobeat", Choice);
        }
        String NextGenre = GetMainGenres(DataRavenUser);
        System.out.println(NextGenre);
        return DataRavenUser;
        }
}
