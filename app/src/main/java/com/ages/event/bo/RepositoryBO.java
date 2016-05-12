package com.ages.event.bo;

import android.app.Activity;

import com.ages.event.repository.repositoryDB;

/**
 * Created by cassio on 09/05/2016.
 */
public class RepositoryBO {

    private final com.ages.event.repository.repositoryDB repositoryDB;

    public RepositoryBO(Activity activity){
        repositoryDB = new repositoryDB(activity);

    }

}
