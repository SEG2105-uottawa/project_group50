package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
//below lines are added
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;


public class CreateAccount extends AppCompatActivity {

    private Button create;
    private EditText usernameField;
    private EditText passwordField;
    private Spinner spinner;
    //below line is added
    DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().hide();

        spinner = findViewById(R.id.spinner);
        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);
        //below line is added
        database = FirebaseDatabase.getInstance().getReference().child("users");

    }

    //When 'Create Account' is clicked, validate fields and if successful create the account
    public void createAccount(View view){
        //TODO: Validate fields
        //TODO: Create Account and send info to firebase

        // get user input from fields
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String role = spinner.getSelectedItem().toString();

        //Checking username
        if(username.length()<=2){
            Toast.makeText(CreateAccount.this,"Sorry, username needs to have at least 3 characters.",Toast.LENGTH_LONG).show();
            return;
        }
        else if( (!username.matches("^[a-zA-Z0-9]+$")) || username.matches("[0-9]+")){
            Toast.makeText(CreateAccount.this,"Sorry, username can contain numbers and letters, but needs to contain at least 1 letter.",Toast.LENGTH_LONG).show();
            return;
        }

        //Checking password
        if (password.length() < 5)  {
            Toast.makeText(CreateAccount.this,"Sorry, your password must have at least 5 characters.",Toast.LENGTH_LONG).show();
            return;
        }
        else if (password.matches("[0-9]+")) {
            Toast.makeText(CreateAccount.this,"Sorry, your password must have at least 1 letter or special character.",Toast.LENGTH_LONG).show();
            return;
        }

        Query query = database.orderByChild("userName").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.hasChildren()){
                    Toast.makeText(CreateAccount.this, "Sorry, the username is already present",Toast.LENGTH_LONG).show();
                }else{
                    if(role.equals("Employee")){
                        EmployeeAccount entry = new EmployeeAccount(username,password,role);
                        database.push().setValue(entry);
                    }else if(role.equals("Customer")){
                        CustomerAccount entry = new CustomerAccount(username,password,role);
                        database.push().setValue(entry);
                    }else if (role.equals("Admin")){
                        AdminAccount entry = new AdminAccount(username,password,role);
                        database.push().setValue(entry);
                    }
                    Toast.makeText(CreateAccount.this,"Successful",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        finish();


    }

    //Called from createAccount() and onClick backButton
    public void returnToMain(View view){
        finish();
    }
}

