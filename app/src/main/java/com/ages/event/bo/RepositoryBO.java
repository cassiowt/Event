package com.ages.event.bo;

import android.app.Activity;

import com.ages.event.model.Noticia;
import com.ages.event.repository.RepositoryDB;

import java.util.List;

/**
 * Created by cassio on 09/05/2016.
 */
public class RepositoryBO {

    private List<Noticia> noticias;
    private final RepositoryDB repositoryDB;

    public RepositoryBO(Activity activity){
        repositoryDB = new RepositoryDB(activity);

    }

    public List<Noticia> listarNoticias(){

        noticias = repositoryDB.listarNoricias();

        return noticias;
    }

}
