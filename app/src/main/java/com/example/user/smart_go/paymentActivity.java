package com.example.user.smart_go;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class paymentActivity extends AppCompatActivity {
    TextView name,id,off;
    ImageView img;
    Button pay_btn;
    JSONObject pay_js;
    DataCenter dataCenter = MainActivity.datacenter;
    String tag = "paymentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        findView();
        set();
    }

    public void set()
    {
        try {

            pay_js = dataCenter.conpon_scanner; //jsonobj
            Log.d(tag,pay_js.toString());
            //obj>>"標籤"tostring
            off.setText(pay_js.getString("off"));
            id.setText(dataCenter.getID());
            name.setText(pay_js.getString("name"));
            img.setImageDrawable(getResources().getDrawable(pay_js.getInt("img")));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void findView()
    {
        name = (TextView) findViewById(R.id.name_txv);
        id = (TextView) findViewById(R.id.id_txv);
        off = (TextView) findViewById(R.id.off_txv);
        img = (ImageView) findViewById(R.id.payimg);
        pay_btn = (Button) findViewById(R.id.paybtu);
    }
}