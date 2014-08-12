package com.kekwanu.smsreceiversample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity{
    private static String TAG = MainActivity.class.getCanonicalName();
    private SMSReceiver smsReceiver;
    private static TextView smsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smsTextView = (TextView)findViewById(R.id.sms_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume(){
        Log.i(TAG, "onResume");

        super.onResume();

        IntentFilter filter = new IntentFilter(SMSReceiver.SMS_RECEIVED);
        smsReceiver = new SMSReceiver();
        registerReceiver(smsReceiver, filter);
    }

    @Override
    protected void onPause(){
        Log.i(TAG,"onPause");

        super.onPause();

        unregisterReceiver(smsReceiver);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //SMS receiver class...listens for received SMS
    public static class SMSReceiver extends BroadcastReceiver {

        private static final String TAG = SMSReceiver.class.getSimpleName();
        private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

        @Override
        public void onReceive(final Context context, final Intent intent) {
            Log.i(TAG, "onReceive");

            if (intent != null && SMS_RECEIVED.equals(intent.getAction())) {
                Log.d("TAG", "onReceive - SMS received in inner class");

                //---get the SMS message passed in---
                Bundle bundle = intent.getExtras();
                SmsMessage[] msgs = null;
                String str = "";

                if (bundle != null){

                    //---retrieve the SMS message received---
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        str += "SMS from " + msgs[i].getOriginatingAddress();
                        str += " :";
                        str += msgs[i].getMessageBody();
                        str += "\n";
                    }

                    smsTextView.setText(str);
                }
            }
        }

    }
}
