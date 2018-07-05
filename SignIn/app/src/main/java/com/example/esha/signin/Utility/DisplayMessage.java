package com.example.esha.signin.Utility;


import android.content.Context;
import android.widget.Toast;

public class DisplayMessage {
    public static void displayMessage(Context context, String msg, boolean longDuration){

        int showMsg = longDuration? Toast.LENGTH_LONG:Toast.LENGTH_SHORT;

        Toast.makeText(context, msg, showMsg).show();//alerts the user of the succes and failure scenario

    }
}