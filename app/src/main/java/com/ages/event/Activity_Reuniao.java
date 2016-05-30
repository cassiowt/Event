package com.ages.event;

import android.os.Bundle;

public class Activity_Reuniao extends DashBoardActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuniao);
        setHeader(getString(R.string.ActivityReuniaoTitle), true);
    }

}
