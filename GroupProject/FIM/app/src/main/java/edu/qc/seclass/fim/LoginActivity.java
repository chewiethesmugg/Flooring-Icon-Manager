package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        findViewById(R.id.bt_Browse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMainActivity();
            }
        });

        findViewById(R.id.bt_EmployeeLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoEmployeeLogin();
            }
        });

    }

    private void gotoEmployeeLogin() {
        Intent intent = new Intent(LoginActivity.this, EmployeeLoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoMainActivity() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
