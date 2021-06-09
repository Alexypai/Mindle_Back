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
import static com.ipiecoles.mindleBack.ravenDB.connexion.DataUser.GetRavenData;
import com.ipiecoles.mindleBack.entity.listGenres;


//                                                  Input , Output
public class Handler implements RequestHandler<DataUser , DataOutput> {
    @Override
    public DataOutput handleRequest(DataUser user, Context context) {
            String spotifyUserID = user.getSpotifyID();
            Integer status = user.getChoose();
            DataOutput Output = new DataOutput();
            if (status.equals(3)) {
                try {
                    listGenres RavenDataUser = GetRavenData(spotifyUserID,status);
                    //Output.outputGenre =  GetData();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                } catch (CertificateException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                Output.outputSpotifyID = spotifyUserID;
                //Output.outputGenre = "Techno";
                // Check si c'est deja un utilisateur
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
