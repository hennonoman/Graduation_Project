package com.hennonoman.waytracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.hennonoman.waytracker.fragments_java.ConfirmRespass;
import com.hennonoman.waytracker.fragments_java.ResetpassFragment;

public class SmsListener extends BroadcastReceiver {


    public static String codeMessage;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                        if( AuthenticationActivity.codenumber!=null)
                        AuthenticationActivity.codenumber.setText(msgBody.substring(0,6));

                        if(  ResetpassFragment.editText!=null)
                        ResetpassFragment.editText.setText(msgBody.substring(0,6));



                    }


                }catch(Exception e){
//                            Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }




}