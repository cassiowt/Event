package com.ages.event.bo;

import android.app.Activity;

import com.ages.event.repository.RepositoryDB;

/**
 * Created by cassio on 09/05/2016.
 */
public class RepositoryBO {

    private final RepositoryDB repositoryDB;

    public RepositoryBO(Activity activity){
        repositoryDB = new RepositoryDB(activity);

    }

}
