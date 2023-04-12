package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class EmployeeLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);


        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameText = findViewById(R.id.et_employeeID);
                EditText passwordText = findViewById(R.id.et_password);
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                loginEmployee(username,password);
            }
        });
    }

    private void loginEmployee(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user!=null){
                    Log.i("employeeLogin","successfully login");
                    gotoEmployeeMainActivity();
                }else{
                    e.printStackTrace();
                    Toast.makeText(EmployeeLoginActivity.this,"error logging in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void gotoEmployeeMainActivity() {
        //normal intent
        Intent intent = new Intent(EmployeeLoginActivity.this, EmployeeMainActivity.class);

        //intent to test addProductActivity


        //intent to test deleteProductActivity
        //Intent intent = new Intent(EmployeeLoginActivity.this,DeleteProductActivity.class);
        //creating test floor object to pass in
        //REMOVE BELOW
        //Floor testObject = new Floor();
        startActivity(intent);
        finish();
    }

}