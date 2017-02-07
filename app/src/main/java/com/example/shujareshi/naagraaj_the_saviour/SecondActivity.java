package com.example.shujareshi.naagraaj_the_saviour;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private static final int mper = 0;
    TextView tvLat, tvLong;
    Location loc;
    Button b, s , ec  , c ;
    String la, lo, ph, msg , ph1;
    EditText phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvLat = (TextView) findViewById(R.id.tvlat);

        tvLong = (TextView) findViewById(R.id.tvlong);

        LocationManager locman = (LocationManager) getSystemService(LOCATION_SERVICE);

        locman.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                tvLat.setText(String.valueOf(location.getLatitude()));
                la=String.valueOf(location.getLatitude());
                tvLong.setText(String.valueOf(location.getLongitude()));
                lo=String.valueOf(location.getLongitude());

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });

        ec  = (Button)findViewById(R.id.ecall);
        ec.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent j  = new Intent(Intent.ACTION_CALL);
                j.setData(Uri.parse("tel:9889606087"));
                startActivity(j);
            }
        }));

        b = (Button) findViewById(R.id.map);
        b.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, MapsActivity.class);
                startActivity(i);
            }

        }));

        s = (Button) findViewById(R.id.send);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSMessage();
            }
        });


        c  = (Button)findViewById(R.id.call);
        c.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                phone = (EditText) findViewById(R.id.phone1);
                ph1 = phone.getText().toString();
                Intent j1  = new Intent(Intent.ACTION_CALL);
                j1.setData(Uri.parse("tel:"+ ph1));
                startActivity(j1);
            }
        }));

    }

    protected void sendSMSMessage() {
        phone = (EditText) findViewById(R.id.phone1);
         ph1 = phone.getText().toString();

        ph = "8860513619";
        msg = "Please Help me , I am in danger \n my location co-ordinates are :\n latitude :"+la+"\n longitude : "+lo;
        try {
            SmsManager smn = SmsManager.getDefault();
            if (ph1 == null)
                smn.sendTextMessage(ph, null, msg, null, null);
            else
                smn.sendTextMessage(ph1, null, msg, null, null);

            Toast.makeText(getApplicationContext(), "SMS SENT ", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS FAILED ..U R DEAD !!! ", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}

       /* if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, mper);
            }
        }*/


 //   @Override
   /* public void onRequestPermissionsResult(int requestcode, String permissions[], int[] grantResults) {
        switch (requestcode) {
            case mper: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(ph, null, msg, null, null);
                    Toast.makeText(getApplicationContext(), "SMS SENT !!", Toast.LENGTH_LONG).show();
                } else

                {
                    Toast.makeText(getApplicationContext(), "SMS FALED  !!... U R DEAD ", Toast.LENGTH_LONG).show();
                    return;
                }
            }

        }

    }*/
    /*public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case mper: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(ph, null, msg, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }*/




