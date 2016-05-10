package com.ages.event;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ages.event.bo.RepositoryBO;

public class MainActivity extends AppCompatActivity {


    private RepositoryBO repositoryBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setProgressBarVisibility(false);
        getSupportActionBar().hide();

        repositoryBO = new RepositoryBO(this);

    }
    public void onButtonClicker(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.main_btn_homeDate:
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
