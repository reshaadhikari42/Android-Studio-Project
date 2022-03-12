package com.example.dms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt_username, txt_message;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_message= (EditText)findViewById(R.id.txt_message);
        txt_username= (EditText)findViewById(R.id.txt_username);

        }


        public void btn_send(View view) {
        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(permissionCheck== PackageManager.PERMISSION_GRANTED){
            MyMessage();
        }
        else ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }

    private void MyMessage() {
        String phoneNumber = txt_username.getText().toString().trim();
        String Message = txt_message.getText().toString().trim();

        if(!txt_username.getText().toString().equals("")||!txt_message.getText().toString().equals("")){

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, Message, null, null);

        Toast.makeText(this, "Message Sent successfully!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Please Enter message or Username", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                }
                else{
                    Toast.makeText(this, "You do not have permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}