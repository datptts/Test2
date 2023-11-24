package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.login1.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding bingding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bingding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(bingding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        bingding.buttonRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = bingding.regisEmail.getText().toString();
                String password = bingding.passregis.getText().toString();
                String confirmPassword = bingding.regispassconfirm.getText().toString();

                if(email.equals("") || password.equals("") || confirmPassword.equals(""))
                    Toast.makeText(RegistrationActivity.this,"all fields are madatory", Toast.LENGTH_SHORT).show();
                else {
                    if(password.equals(confirmPassword)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);

                        if(checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email,password);
                            if( insert == true){
                                Toast.makeText(RegistrationActivity.this,"hoang thanh ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(RegistrationActivity.this,"failed ", Toast.LENGTH_SHORT).show();

                            }
                        }else {
                            Toast.makeText(RegistrationActivity.this,"user already exists, please login", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        Toast.makeText(RegistrationActivity.this,"invalid password", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });



    }

    public void login(View view) {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }


}