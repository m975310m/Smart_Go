package com.example.user.smart_go;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.CompoundBarcodeView;


/**
 *  掃描頁面物件控制 (頁面介面元件 activity_scanner.xml)
 *      閃光燈開關按鈕
 *      從好友清單按鈕選取:
 *          進入好友葉面 (FriendList)
 */
public class ScannerActivity extends AppCompatActivity implements CompoundBarcodeView.TorchListener
{
    private CaptureManager capture;
    private CompoundBarcodeView barcodeScannerView;
    private Button switchFlashlightButton;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        getIntentData();
        barcodeScannerView = (CompoundBarcodeView)findViewById(R.id.zxing_barcode_scanner);
        barcodeScannerView.setTorchListener(this);

//        switchFlashlightButton = (Button)findViewById(R.id.switch_flashlight);

        if (!hasFlash())
        {
            switchFlashlightButton.setVisibility(View.GONE);
        }

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }

    private void getIntentData()
    {
        intent=getIntent();
    }

    /**
     * Check if the device's camera has a Flashlight.
     * @return true if there is Flashlight, otherwise false.
     */
    private boolean hasFlash()
    {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public void switchFlashlight(View view)
    {
//        if (new String("開啟閃光燈").equals(switchFlashlightButton.getText())) {
//            barcodeScannerView.setTorchOn();
//        } else {
//            barcodeScannerView.setTorchOff();
//        }
    }

    @Override
    public void onTorchOn()
    {
//        switchFlashlightButton.setText("關閉閃光燈");
    }

    @Override
    public void onTorchOff() {
//        switchFlashlightButton.setText("開啟閃光燈");
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

}
