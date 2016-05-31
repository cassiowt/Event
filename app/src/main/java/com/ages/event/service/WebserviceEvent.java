package com.ages.event.service;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ages.event.R;
import com.ages.event.repository.RepositoryDB;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebserviceEvent  {

    RepositoryDB sqlite_obj;
    boolean atualizado = false;
    Button get, store;
    List<String> list1, list2, list3, list4, list5, list6;
    InputStream is = null;

    String ip = "http://www.ages.pucrs.br/Event/ws/noticias/listarTodas/ATIVO";
   // String ip = "http://10.32.223.17:8080/Event/ws/noticias/listarTodas/ATIVO";
    String line = null;
    String result = null;

    public boolean service(Activity activity) {



// teste se existe conexão

        ConnectivityManager connMgr = (ConnectivityManager)
                activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            System.out.println("INTERNET OK: ");
        } else {
            System.out.println("INTERNET NÃO ok: ");
        }


        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            sqlite_obj = new RepositoryDB(activity);

            webservice();

           }

        return atualizado;
    }


    private void webservice() {
        try {
            URL url = new URL(ip);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.getContent();
            is = connection.getInputStream();

        } catch (Exception e) {
            Log.e("ERRO", "Webservice 1", e);
            atualizado = false;
        }
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {

                sb.append(line + "\n");
            }

            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("Webservice 2", e.toString());
            atualizado = false;
        }
        try {
           JSONObject jo = new JSONObject(result);
           JSONArray ja = jo.getJSONArray("noticia");;

         list1 = new ArrayList<String>();
            list2 = new ArrayList<String>();
            list3 = new ArrayList<String>();
            list4 = new ArrayList<String>();
            list5 = new ArrayList<String>();
            list6 = new ArrayList<String>();

            for (int i = 0; i < ja.length(); i++) {

                jo = ja.getJSONObject(i);
                list1.add(jo.getString("idNoticia"));
                list2.add(jo.getString("titulo"));
                list3.add(jo.getString("texto"));
                list4.add(jo.getString("status"));
                list5.add(jo.getString("dtCadastro"));
                list6.add(jo.getString("dtAlteracao"));
            }

            sqlite();
        } catch (Exception e) {
            Log.e("ERRO","Webservice 3", e);
            atualizado = false;
        }

    }


    private void sqlite() {
        sqlite_obj.open();
        sqlite_obj.deleteAllNoticias();

        for (int i = 0; i < list1.size(); i++) {
            sqlite_obj.insertNoticia(list1.get(i).toString(),
                    list2.get(i).toString(),
                    list3.get(i).toString(),
                    list4.get(i).toString(),
                    Long.valueOf(list5.get(i)),
                    Long.valueOf(list6.get(i)));
        }
        sqlite_obj.close();
        atualizado = true;
    }
}
