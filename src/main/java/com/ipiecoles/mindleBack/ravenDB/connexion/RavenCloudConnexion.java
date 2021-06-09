package com.ipiecoles.mindleBack.ravenDB.connexion;

import com.ipiecoles.mindleBack.entity.listGenres;
import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class RavenCloudConnexion {

    static final String URL = "https://a.free.mindle.ravendb.cloud/";
    static final String DATABASE = "mindle";
    public static IDocumentSession session;

    public static void main(String[] args) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {

    }

    public static KeyStore GetCert () throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore clientStore = KeyStore.getInstance("PKCS12");
        ClassPathResource res = new ClassPathResource("free.mindle.client.certificate.pfx");
        File cert = res.getFile();
        clientStore.load(new FileInputStream(cert), "".toCharArray());
        return clientStore;
    }


    public static listGenres ConnexionRaven(String SpotifyID) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {

        KeyStore clientStore = GetCert();
        listGenres DataRavenUser = null;

        try (DocumentStore store = new DocumentStore()) {
            store.setCertificate(clientStore);
            store.setDatabase(DATABASE);
            store.setUrls(new String[]{URL});
            store.initialize();
            session = store.openSession();
            Boolean ExistingUser = session.advanced().exists(SpotifyID);
            if (ExistingUser.equals(true)){
                 DataRavenUser = session.load(listGenres.class, SpotifyID);
                System.out.println("Not New");
                System.out.println(DataRavenUser.listGenres.toArray());
            }else{
                listGenres NewUser = session.load(listGenres.class, "ModelNewUser"); // id du document new utilisateur
                 DataRavenUser = ConnexionRavenNewUser(NewUser,SpotifyID);
                System.out.println("New user");
            }
        }
        return DataRavenUser;
    }

    public static listGenres ConnexionRavenNewUser(listGenres NewUser, String SpotifyID) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {

        KeyStore clientStore = GetCert();

        try (DocumentStore store = new DocumentStore()) {
            store.setCertificate(clientStore);
            store.setDatabase(DATABASE);
            store.setUrls(new String[]{URL});
            store.initialize();
            IDocumentSession session2 = store.openSession();

            session2.store(NewUser,SpotifyID);
            session2.saveChanges();
            return ConnexionRaven(SpotifyID);
        }
    }
}
