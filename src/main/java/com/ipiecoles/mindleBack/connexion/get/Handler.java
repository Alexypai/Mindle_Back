package com.ipiecoles.mindleBack.connexion.get;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ipiecoles.mindleBack.connexion.send.DataOutput;

import java.io.IOException;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

//import static com.ipiecoles.mindleBack.MindleBackApplication.GetData;
import static com.ipiecoles.mindleBack.algorithme.MainAlgorithme.mainMethode;
import static com.ipiecoles.mindleBack.ravenDB.connexion.RavenCloudConnexion.ConnexionRaven;
//import static com.ipiecoles.mindleBack.algorithme.RavenManage.GetMainGenres;
import com.ipiecoles.mindleBack.entity.listGenres;


//                                                  Input , Output
public class Handler implements RequestHandler<DataUser , DataOutput> {
    @Override
    public DataOutput handleRequest(DataUser user, Context context) {
            String spotifyUserID = user.getSpotifyID();
            Integer status = user.getChoose();
            String genre = user.getGenre();
            DataOutput Output = new DataOutput();
            if (status.equals(0) || status.equals(1) || status.equals(2) || status.equals(3)) {
                listGenres DataRavenUser = null;
                try {
                    DataRavenUser = ConnexionRaven(spotifyUserID);
                } catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException e) {
                    e.printStackTrace();
                }
                if (status != 0){
                    try {
                        String result = mainMethode(DataRavenUser,genre,status,spotifyUserID);
                        Output.outputGenre = result;
                    } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
                        e.printStackTrace();
                    }
                }
                    Output.outputSpotifyID = spotifyUserID;
            } else {
                Output.outputSpotifyID = spotifyUserID;
                Output.outputGenre = "Don't Work";
                // utilisation de l'algo
            }

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "applicatton/json");
            headers.put("access-Control-Allow-Origin", "https://testmindle.free.beeceptor.com");
            int statusCode = 200;

            return Output;
    }

}
