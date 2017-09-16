package com.example.gnecco.trabalhotcc.makecall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gnecco.trabalhotcc.R;

public class MakeCallActivity extends AppCompatActivity {


    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    private Button btLigar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_call);

        btLigar = (Button) findViewById(R.id.btLigar);
        btLigar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(MakeCallActivity.this,
                            Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(MakeCallActivity.this,
                                Manifest.permission.CALL_PHONE)) {

                        } else {

                            ActivityCompat.requestPermissions(MakeCallActivity.this,
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
                        }
                    }
                    else
                    {
                        ligar();
                    }
                }
                else
                {
                    ligar();
                }
            }

        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    ligar();

                } else {
                    Toast.makeText(MakeCallActivity.this, R.string.permission_denied_call,Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


    public void ligar()
    {
        try
        {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:+5511982920823"));
            startActivity(callIntent);
        }
        catch (SecurityException e)
        {
            Toast.makeText(MakeCallActivity.this, R.string.call_error,Toast.LENGTH_LONG).show();

        }
    }
}
