package com.example.user.smart_go;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONException;

public class Exchange_Activity extends AppCompatActivity {
    private ImageView qrimg;
    private static final String TAG = "exchange";
    DataCenter datacenter = MainActivity.datacenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_);
        findView();
        buildQRCode();
        Log.d(TAG,"優惠QR建立完成");
    }

    private void buildQRCode()
    {
        try {
            new BuilderQRcode(qrimg,datacenter.id).build();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void findView()
    {
        qrimg = (ImageView)findViewById(R.id.qr_img);
    }
}
