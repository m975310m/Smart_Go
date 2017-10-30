package com.example.user.smart_go;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.smart_go.view.gpsData;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity 
{
    public static DataCenter datacenter = new DataCenter();
    private ImageView exchange_img,runpoint_img,Inquire_img,record_img,scanning_img,news_img;
    private Button testbutton;
    String TAG = "MainActivity";
    gpsData gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gps = new gpsData();
        gps.loadGpsData(MainActivity.this);
        findView();
        setOnClickEvent();
        setID();



    }

    private void setID()
    {
        datacenter.id="TestID";
    }

    private void setOnClickEvent()
    {
        exchange_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this,Exchange_Activity.class));
            }
        });
        scanning_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                new IntentIntegrator(MainActivity.this).setOrientationLocked(false).setCaptureActivity(ScannerActivity.class).initiateScan(); // `this` 放你在的Activity
            }
        });
        runpoint_img.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            startActivity(new Intent(MainActivity.this,RunpointActivity.class));
        }
    });

        testbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(gps.getLocationLoadSuccess()){
                    Log.d(TAG, String.valueOf(gps.getPlace().latitude));
                }

            }
        });
    }

    private void findView()
    {
        exchange_img = (ImageView)findViewById(R.id.exchange_img);
        runpoint_img = (ImageView)findViewById(R.id.runpoint_img);
        record_img = (ImageView)findViewById(R.id.record_img);
        Inquire_img = (ImageView)findViewById(R.id.Inquire_img);
        scanning_img = (ImageView)findViewById(R.id.scanning_img);
        news_img = (ImageView)findViewById(R.id.news_img);
        testbutton = (Button)findViewById(R.id.test_btn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                ;
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
