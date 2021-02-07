package com.example.patientmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.patientmodule.helper.SQLiteHandler;
import com.example.patientmodule.helper.SessionManager;


public class homeDashboard extends AppCompatActivity {

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dashboard);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());
    }

    public void appointmentAction(View view) {
        Toast.makeText(this, "hi this is home",Toast.LENGTH_SHORT).show();
    }

    public void profileAction(View view) {

    }

    public void logoutAction(View view) {
        session.setLogin(false);

        db.deleteUsers();
        Toast.makeText(this,"loggin out",Toast.LENGTH_SHORT).show();
        // Launching the login activity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
