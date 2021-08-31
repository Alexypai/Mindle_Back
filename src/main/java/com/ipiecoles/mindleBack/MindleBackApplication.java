package com.ipiecoles.mindleBack;

/*

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.*;


public class MindleBackApplication {

	static final String URL = "https://a.free.mindle.ravendb.cloud/";
	static final String DATABASE = "mindle";

	static IDocumentStore store;
	static IDocumentSession session2;

	static listGenres genresList;

	public static void main(String[] args) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {

	}


	public static String GetData() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
		KeyStore clientStore = KeyStore.getInstance("PKCS12");

		*/
/*//*
/// Acces Local à RavenDB
		KeyStore clientStore = KeyStore.getInstance("PKCS12");
		clientStore.load(new FileInputStream("C:\\Alexy_Docs\\Université - Ecole\\CDEV\\Messi\\Mindle_back\\Mindle_Back\\Certificat\\free.mindle.client.certificate.pfx"), "".toCharArray());
		*//*


		/// Acces distant à RavenDB
		ClassPathResource res = new ClassPathResource("free.mindle.client.certificate.pfx");
		File cert = res.getFile();
		clientStore.load(new FileInputStream(cert), "".toCharArray());


		try (DocumentStore store = new DocumentStore())  {
			store.setCertificate(clientStore);
			store.setDatabase(DATABASE);
			store.setUrls(new String[]{ URL });

			store.initialize();
			session = store.openSession();
			genresList = session.load(listGenres.class, "bfd0f09a-2172-4153-97e5-0f549c413fe2");

			// do your work here
		}
		//CreateNewUser("Spotify ID");
		//genresList = session.load(listGenres.class, "modelNewUser");
		//System.out.println("la liste contient : " + genresList.listGenres.size() + "genres");
		//AddDataUsers("Bosanova");
		String retour = GetMainGenres();
		return retour;

	}


	public static IDocumentStore CreateStore() {
		store = new DocumentStore(new String[]{URL}, DATABASE);
		//DocumentConventions conventions = store.getConventions();
		store.initialize();
		return store;
	}

	public static void AddDataUsers(String NewGenres){
		List<String> mainGenres = null;
		for (sousGenres sousGenre: genresList.listGenres) {
			if(sousGenre.Name.equals(NewGenres)){
				System.out.println("oui");
				sousGenre.Total = sousGenre.Total + 1;
				mainGenres = sousGenre.Genre;
				System.out.println(mainGenres);
				}
			}
		if (mainGenres != null){
			for (sousGenres sousGenre: genresList.listGenres) {
				for (String mainGenre : mainGenres){
					if (mainGenre.equals(sousGenre.Name)){
						sousGenre.Total = sousGenre.Total + 1;
					}
				}
			}
		}
		session.saveChanges();
	}

	public static String GetMainGenres(){
		ArrayList<ArrayList<String>> Totals = new ArrayList();
		List<String> listMainGenres = Arrays.asList("Jazz", "rnb");
		for (String MainGenre : listMainGenres) {
			for (sousGenres sousGenre: genresList.listGenres) {
				if (sousGenre.Name.equals(MainGenre)){
					ArrayList<String> Provisoire = new ArrayList();
					Provisoire.add(sousGenre.Name);
					Provisoire.add(sousGenre.Total.toString());
					Totals.add(Provisoire);
				}
			}
		}
		System.out.println(Totals);
		return "Jazz";
	}


}

*/
