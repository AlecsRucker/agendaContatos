package com.example.appdiaristas;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;
public class CxMsg {

   /* Activity act;
    void CxMsg (Activity act){
        this.act = act;
    }*/

    public static void mostrar(String txt, Activity act){
        AlertDialog.Builder abd=new AlertDialog.Builder(act);
        abd.setMessage(txt);
        abd.setNeutralButton("OK", null);
        abd.show();
    }
}
