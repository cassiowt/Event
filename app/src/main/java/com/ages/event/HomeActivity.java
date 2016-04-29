package com.ages.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * Created by cassio on 28/04/2016.
 */
public class HomeActivity extends DashBoardActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        setHeader(getString(R.string.HomeActivityTitle), true, true);
    }

    /**
     * Button click handler on Main activity
     * @param v
     */
    public void onButtonClicker(View v)
    {
        Intent intent;

        switch (v.getId()) {
            case R.id.main_btn_eclair:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;

            case R.id.main_btn_froyo:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;

            case R.id.main_btn_gingerbread:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;

            case R.id.main_btn_honeycomb:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;

            case R.id.main_btn_ics:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;

            case R.id.main_btn_jellybean:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
