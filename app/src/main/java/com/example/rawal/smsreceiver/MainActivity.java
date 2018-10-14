package com.example.rawal.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends BroadcastReceiver {

    // Get the object of SmsManager

    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get( "pdus" );
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu( (byte[]) pdusObj[i] );
                    String message = currentMessage.getDisplayMessageBody();
                    Log.i( "SmsReceiver", "Message Received : " + message );
                    Toast.makeText( context, "Message Received : " + message , Toast.LENGTH_LONG ).show();
                }
            }
        }
        catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
        }
    }

}