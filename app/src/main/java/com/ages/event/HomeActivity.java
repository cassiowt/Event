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
            case R.id.evento_btn_1:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;

            case R.id.evento_btn_2:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;

            case R.id.evento_btn_6:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;

            case R.id.evento_btn_3:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;

            case R.id.evento_btn_4:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;

            case R.id.evento_btn_5:
                intent = new Intent(this, Activity_Evento_1.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
