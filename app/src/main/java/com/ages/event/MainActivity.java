package com.ages.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ages.event.bo.RepositoryBO;
import com.ages.event.model.Noticia;
import com.ages.event.service.WebserviceEvent;
import com.ages.event.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RepositoryBO repositoryBO;
    private WebserviceEvent webserviceEvent;
    private List<Noticia> listaNoticias;
    private ListView lstNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setProgressBarVisibility(false);
        getSupportActionBar().hide();

        repositoryBO = new RepositoryBO(this);

 /*       webserviceEvent  = new WebserviceEvent();

         if(webserviceEvent.service(this)){
             Util.showMsgToast(this, "Não foi possivel atualizar");
         }
*/

        listaNoticias = repositoryBO.listarNoticias();

        lstNoticias = (ListView) findViewById(R.id.lstNoticias);

        ArrayList<String> valores = new ArrayList<String>();

        for (Noticia n: listaNoticias){
            valores.add(n.getTitulo() +"\n" + n.getTexto());
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, valores);

        lstNoticias.setAdapter(adapter);





    }
    public void onButtonClicker(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.main_btn_homeDate:
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.main_btn_homeHoras:
                intent = new Intent(this, Webservice_Activity.class);
                startActivity(intent);
                break;
            case R.id.main_btn_homeSalas:

                break;
        }
    }
}
