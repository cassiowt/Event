package com.ages.event.old;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.ages.event.model.Noticia;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cassio on 26/05/2016.
 */
public class NoticiaJsonActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_activityist_news);
        new DownloadJsonAsyncTask()
                .execute("http://127.0.0.1:8080/Event/ws/noticias/listarTodas/ATIVO");
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Noticia noticia = (Noticia) l.getAdapter().getItem(position);

        Intent intent = new Intent(this, InformacoesActivity.class);
       // intent.putExtra("noticia", noticia);
        startActivity(intent);
    }

    class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Noticia>> {
        ProgressDialog dialog;

        //Exibe pop-up indicando que está sendo feito o download do JSON
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(NoticiaJsonActivity.this, "Aguarde",
                    "Fazendo download do JSON");
        }


        //Acessa o serviço do JSON e retorna a lista de pessoas
        @Override
        protected List<Noticia> doInBackground(String... params) {
            String urlString = params[0];
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(urlString);
            try {
                HttpResponse response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    String json = getStringFromInputStream(instream);
                    instream.close();
                    List<Noticia> noticias = getNoticias(json);
                    return noticias;
                }
            } catch (Exception e) {
                Log.e("Erro", "Falha ao acessar Web service", e);
            }
            return null;
        }

        //Retorna uma lista de pessoas com as informações retornadas do JSON
        private List<Noticia> getNoticias(String jsonString) {
            List<Noticia> noticias = new ArrayList<Noticia>();
            try {
                JSONArray noticiasJson = new JSONArray(jsonString);
                JSONObject noticia;

                for (int i = 0; i < noticiasJson.length(); i++) {
                    noticia = new JSONObject(noticiasJson.getString(i));
                    Log.i("NOTICIA ENCONTRADA: ",
                            "titulo =" + noticia.getString("titulo"));

                    Noticia objetoNoticia = new Noticia();
                    objetoNoticia.setTitulo(noticia.getString("titulo"));
                    objetoNoticia.setTexto(noticia.getString("texto"));
                    noticias.add(objetoNoticia);
                }

            } catch (JSONException e) {
                Log.e("Erro", "Erro no parsing do JSON", e);
            }
            return noticias;
        }

        //Converte objeto InputStream para String
        private String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return sb.toString();

        }
    }
}