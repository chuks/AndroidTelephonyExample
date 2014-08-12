package com.kekwanu.telephonyexample1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView phoneTypeText;
    private TextView phoneIdText;
    private TextView phoneSoftwareVersionText;
    private TextView phoneNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneTypeText = (TextView)findViewById(R.id.phone_type);
        phoneIdText = (TextView)findViewById(R.id.phone_id);
        phoneSoftwareVersionText = (TextView)findViewById(R.id.phone_software);
        phoneNumberText = (TextView)findViewById(R.id.phone_number);


        String srvcName = Context.TELEPHONY_SERVICE;
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(srvcName);

        int phoneType = telephonyManager.getPhoneType();

        switch(phoneType){
            case TelephonyManager.PHONE_TYPE_CDMA:
                phoneTypeText.setText("CDMA");

                break;
            case TelephonyManager.PHONE_TYPE_GSM:
                phoneTypeText.setText("GSM");

                break;
            case TelephonyManager.PHONE_TYPE_NONE:
                phoneTypeText.setText("NONE");

                break;
        }

        phoneIdText.setText(telephonyManager.getDeviceId());
        phoneSoftwareVersionText.setText(telephonyManager.getDeviceSoftwareVersion());
        phoneNumberText.setText(telephonyManager.getLine1Number());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
}
