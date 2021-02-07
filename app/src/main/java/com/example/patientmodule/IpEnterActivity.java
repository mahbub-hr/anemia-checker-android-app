package com.example.patientmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IpEnterActivity extends AppCompatActivity {

    private EditText ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_enter);
        ip = findViewById(R.id.ip);
    }

    public void okAction(View view) {
        Intent intent = new Intent(IpEnterActivity.this, LoginActivity.class);
        intent.putExtra("ip",ip.getText().toString().trim());
        startActivity(intent);

        finish();
    }
}
