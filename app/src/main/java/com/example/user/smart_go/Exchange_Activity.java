package com.example.user.smart_go;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Exchange_Activity extends AppCompatActivity {
    ImageView qrimg;
    Calendar now;
    Handler handle_couponRebuild;
    TextView min_txv,sec_txv;
    int year,month,date,hour,min,sec;
    private static final String TAG = "exchange";
    DataCenter datacenter = MainActivity.datacenter;
    JSONArray counpons;
    AlertDialog.Builder dialog;


    public Exchange_Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_);
        findView();
        productjs();
        Menu();


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
                            qrRebuildTimer();

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

    private void qrRebuildTimer()
    {
        handle_couponRebuild = new Handler();
        final Runnable runnable = new Runnable()
        {
            int count = 0;

            public void run()
            {
                count++;
                if(count%(5*60)==0)
                {
                    reBuildQRCode();
                    sec_txv.setText("00");
                    min_txv.setText("5");
                }
                if(!((Integer.parseInt(sec_txv.getText().toString())-1)==-1))
                {
                    if((Integer.parseInt(sec_txv.getText().toString())-1) < 10)
                    {
                        sec_txv.setText("0"+String.valueOf(Integer.parseInt(sec_txv.getText().toString())-1));
                    }
                    else
                        sec_txv.setText(String.valueOf(Integer.parseInt(sec_txv.getText().toString())-1));
                }
                else
                {
                    if(!((Integer.parseInt(min_txv.getText().toString())-1)==-1))
                    {
                        sec_txv.setText("59");
                        min_txv.setText(String.valueOf(Integer.parseInt(min_txv.getText().toString())-1));
                    }
                    else
                    {
                        qrRebuildTimer();
                    }
                }
                Log.d("handler","QrcodeActivity.qrRebuildTimer="+count);
                handle_couponRebuild.postDelayed(this, 1*1000);
            }
        };
        handle_couponRebuild.post(runnable);
    }



    private void reBuildQRCode()
    {
        try
        {
            now =  Calendar.getInstance(); //讀取現在時間
            loadTime();
            Long deadDatetime = year* 10000000000L+ month*100000000 + date*1000000 + hour*10000+min*500+sec*1;
            new BuilderQRcode(qrimg, counpons.toString()).build();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

    }



    private void loadTime()
    {
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH)+1;
        date = now.get(Calendar.DATE);
        hour = now.get(Calendar.HOUR_OF_DAY);
        min = now.get(Calendar.MINUTE);
        sec = now.get(Calendar.SECOND);
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
        min_txv = (TextView) findViewById(R.id.min_txv);
        sec_txv = (TextView) findViewById(R.id.sec_txv);

    }
}
