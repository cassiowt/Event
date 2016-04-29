package com.ages.event;

import android.os.Bundle;
/**
 * Created by cassio on 28/04/2016.
 */
public class Activity_Evento_1 extends DashBoardActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_1);
        setHeader(getString(R.string.ActivityEvento1Title), true, true);
    }
}
