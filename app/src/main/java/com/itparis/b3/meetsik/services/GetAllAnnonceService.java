package com.itparis.b3.meetsik.services;

import android.app.IntentService;
import android.content.Intent;

import com.itparis.b3.meetsik.beans.Annonce;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Bouveti on 11/03/2015.
 */
public class GetAllAnnonceService extends IntentService {

    private ArrayList<Annonce> allAnnonces;
    private boolean isDataLoaded;

    public GetAllAnnonceService(){
        super("GetAllAnnonceService");
    }

    public GetAllAnnonceService(String nom){
        super(nom);
    }

    public void setDataLoaded(boolean dataLoaded){
        isDataLoaded = dataLoaded;
    }

    public boolean isDataLoaded(){
        return isDataLoaded;
    }

    @Override
    protected void onHandleIntent(Intent intent){

        HttpURLConnection urlConnect = null;
        URL url = null;

        InputStream in = null;

        try{

            url = new URL("localhost/Meetsik/web/app_dev.php/annoncepriceall");
            urlConnect =  (HttpURLConnection)url.openConnection();
            if(urlConnect.getResponseCode() == 200){
                in = urlConnect.getInputStream();
                String json = IOUtils.toString(in,"UTF-8");
                System.out.println(json);
                loadFromJSON("UTF-8");
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                urlConnect.disconnect();
                e.printStackTrace();
            }
        }
    }
    public void loadFromJSON(String json) throws ParseException{
        JSONArray annonces;

        try {
            annonces = new JSONArray(json);
            allAnnonces = new ArrayList<Annonce>();
            for (int i=0;i<annonces.length();i++) {

                JSONObject jsonAnnonce = annonces.getJSONObject(i);
                Annonce annonce = new Annonce(jsonAnnonce.getInt("id"),jsonAnnonce.getString("nom"),jsonAnnonce.getInt("prix"),jsonAnnonce.getString("date"));
                allAnnonces.add(annonce);

            }
            setDataLoaded(true);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}