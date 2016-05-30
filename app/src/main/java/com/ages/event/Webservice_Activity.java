package com.ages.event;

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

public class Webservice_Activity extends Activity {

    RepositoryDB sqlite_obj;

    Button get, store;
    List<String> list1, list2, list3, list4, list5, list6;
    InputStream is = null;

    //String ip = "http://www.ages.pucrs.br/Event/ws/noticias/listarTodas/ATIVO";
    String ip = "http://10.32.223.17:8080/Event/ws/noticias/listarTodas/ATIVO";
    String line = null;
    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webservice);


        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            sqlite_obj = new RepositoryDB(Webservice_Activity.this);

            get = (Button) findViewById(R.id.button1);
            store = (Button) findViewById(R.id.button2);

            get.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    webservice();
                    Toast.makeText(getBaseContext(), "Success : Webservice Call", Toast.LENGTH_SHORT).show();
                }
            });

            store.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    sqlite();
                    Toast.makeText(getBaseContext(), "Stored in SQLite DB", Toast.LENGTH_SHORT).show();
                }
            });
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
    }

    private void webservice() {
        try {

            // teste se existe conexão

            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                System.out.println("INTERNET OK: ");
            } else {
                System.out.println("INTERNET NÃO ok: ");
            }

            URL url = new URL(ip);
/*

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
// read the response
            System.out.println("Response Code: " + conn.getResponseCode());
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            System.out.println(response);
*/

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.getContent();
            is = connection.getInputStream();

        } catch (Exception e) {
            Log.e("ERRO", "Webservice 1", e);
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
        }
        try {
            JSONObject jo = new JSONObject(result);
           JSONArray ja = jo.getJSONArray("noticia");;
            //JSONObject jo = ja.getJSONObject(0);
           // JSONObject jo = ja.getJSONObject(0);


            /*    JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
*/
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
        }
    }
}
