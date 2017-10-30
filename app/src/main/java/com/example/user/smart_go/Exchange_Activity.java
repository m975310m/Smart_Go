package com.example.user.smart_go;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Exchange_Activity extends AppCompatActivity {
    private ImageView qrimg;
    private static final String TAG = "exchange";
    DataCenter datacenter = MainActivity.datacenter;
    JSONArray counpons;
    AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_);
        findView();
        productjs();
        Menu();
        Log.d(TAG,"優惠QR建立完成");
    }

    private void Menu()
    {

        ListAdapter adapterItem = new ListAdapter(Exchange_Activity.this,counpons );

        dialog = new AlertDialog.Builder(Exchange_Activity.this)
                .setTitle("請選擇兌換優惠券")
                .setAdapter(adapterItem, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        try {
                            new BuilderQRcode(qrimg,counpons.getJSONObject(i).toString()).build();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            Log.d("兌換",counpons.getJSONObject(i).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).setNegativeButton("", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
//                        new IntentIntegrator(TransferBalanceActivity.this).setOrientationLocked(false).setCaptureActivity(ScannerActivity.class).initiateScan();

                    }

                });
        dialog.show();
    }


    private void productjs() {
        counpons = new JSONArray();
        JSONObject coupon1 = new JSONObject();
        JSONObject coupon2 = new JSONObject();

        try {
            coupon1.put("name","鳳梨酥");
            coupon1.put("off",30);
            coupon1.put("bonus",100);
            coupon1.put("img",R.drawable.pineapple);


            coupon2.put("name","牛肉麵");
            coupon2.put("off",20);
            coupon2.put("bonus",80);
            coupon2.put("img",R.drawable.beef);

            counpons.put(coupon1);
            counpons.put(coupon2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
