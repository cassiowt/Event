package com.ages.event;

import android.os.Bundle;
/**
 * Created by cassio on 28/04/2016.
 */
public class Activity_Eclair extends DashBoardActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eclair);
        setHeader(getString(R.string.EclairActivityTitle), true, true);
    }
}
