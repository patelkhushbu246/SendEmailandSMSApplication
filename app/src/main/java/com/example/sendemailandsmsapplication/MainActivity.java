package com.example.sendemailandsmsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText fntxt,lntxt,emailtxt,conttxt,passtxt,cpasstxt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fntxt=findViewById(R.id.fname_tv);
        lntxt=findViewById(R.id.lname_tv);
        emailtxt=findViewById(R.id.email_tv);
        conttxt=findViewById(R.id.contact_tv);
        passtxt=findViewById(R.id.pwd_tv);
        cpasstxt=findViewById(R.id.cpwd_tv);
        btn=findViewById(R.id.register_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                        sendSMS();
                    }else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }
                sendEmail();

            }
        });
    }

    private void sendSMS() {
        String fname=fntxt.getText().toString();
        String lname=lntxt.getText().toString();
        String email=emailtxt.getText().toString();
        String contact=conttxt.getText().toString();
        String password=passtxt.getText().toString();
        try {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(contact,null,email,null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sendEmail() {
        String fname=fntxt.getText().toString();
        String lname=lntxt.getText().toString();
        String email=emailtxt.getText().toString();
        String contact=conttxt.getText().toString();
        String password=passtxt.getText().toString();
        String cpass=cpasstxt.getText().toString();

        JavaMailAPI javaMailAPI=new JavaMailAPI(this,fname,lname,email,contact,password);
        javaMailAPI.execute();

        if (password.equals(cpass)){
            Intent i=new Intent(MainActivity.this,HomeActivity.class);
            i.putExtra("Fname",fname);
            i.putExtra("Lname",lname);
            i.putExtra("Email",email);
            i.putExtra("Contact",contact);
            startActivity(i);

        }    }
}