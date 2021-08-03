package com.example.sendemailandsmsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView txt_fn,txt_ln,txt_email,txt_cno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txt_fn=findViewById(R.id.fn);
        txt_ln=findViewById(R.id.ln);
        txt_email=findViewById(R.id.email);
        txt_cno=findViewById(R.id.cno);
        String fname=getIntent().getStringExtra("Fname");
        String lname=getIntent().getStringExtra("Lname");
        String email=getIntent().getStringExtra("Email");
        String contact=getIntent().getStringExtra("Contact");

        txt_fn.setText(fname);
        txt_ln.setText(lname);
        txt_email.setText(email);
        txt_cno.setText(contact);
    }
}