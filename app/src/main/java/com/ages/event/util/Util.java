package com.ages.event.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.ages.event.R;

/**
 * Created by cassio on 22/04/2016.
 */
public class Util {

    public static void showMsgToast(Activity activity, String txt){
        // criado e inicializado um lay-out lytToast
        LayoutInflater inflater = activity.getLayoutInflater();
        View lytToast = inflater.inflate(R.layout.toast_template, (ViewGroup) activity.findViewById(R.id.lytToast));

        TextView txtToast = (TextView) lytToast.findViewById(R.id.txtToast);
        txtToast.setText(txt);

        // criado um novo Toast e inicializado ele com o layo-out lytToast
        Toast toast = new Toast(activity);
        toast.setView(lytToast);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.show();
    }

    public static void showMsgAlertOK(final Activity activity, String titulo, String txt, TipoMsg tipoMsg){
        int theme = 0, icon = 0;
        switch (tipoMsg){
            case INFO:
                theme = R.style.AppTheme_Dark_Dialog_Info;
                icon = R.drawable.info;
                break;
            case ERRO:
                theme = R.style.AppTheme_Dark_Dialog_Erro;
                icon = R.drawable.error;
                break;
            case ALERTA:
                theme = R.style.AppTheme_Dark_Dialog_Alert;
                icon = R.drawable.alerta;
                break;
            case SUCESSO:
                theme = R.style.AppTheme_Dark_Dialog_Sucess;
                icon = R.drawable.sucess;
                break;
        }

        final AlertDialog alertDialog = new AlertDialog.Builder(activity, theme).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);
        alertDialog.setIcon(icon);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(alertDialog.getWindow().getAttributes());
        params.width  = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        alertDialog.show();
        alertDialog.getWindow().setAttributes(params);
    }
}
