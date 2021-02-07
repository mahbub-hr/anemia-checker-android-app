package com.example.patientmodule;
 
import com.example.patientmodule.helper.SQLiteHandler;
import com.example.patientmodule.helper.SessionManager;
 
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
 
    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
 
        txtName = (TextView) findViewById(R.id.fname);
        txtEmail = (TextView) findViewById(R.id.phone);
        btnLogout = (Button) findViewById(R.id.btnLogout);
 
        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
 
        // session manager
        session = new SessionManager(getApplicationContext());
 
        if (!session.isLoggedIn()) {
            logoutUser();
        }
 
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();
 
        String fname = user.get("fname");
        String email = user.get("phone_no");
 
        // Displaying the user details on the screen
        txtName.setText(fname);
        txtEmail.setText(email);
 
        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }
 
    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);
 
        db.deleteUsers();
 
        // Launching the login activity
        Intent intent = new Intent(Home.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}