package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//below codes are added
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //Initializing login button for welcome screen
    private Button loginButton;
    EditText userName;
    EditText password;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        userName = (EditText) findViewById(R.id.usernameField);
        password = (EditText) findViewById(R.id.passwordField);
        reference = FirebaseDatabase.getInstance().getReference("users");
    }

    //When 'Create Account' is clicked, move to that activity
    public void MoveToCreateAccount(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
        startActivityForResult(intent, 0);
    }

    //When 'Login' is clicked, process fields and move to Welcome if successful
    public void AttemptLogin(View view) {
        Query query = reference.orderByChild("userName").equalTo(userName.getText().toString());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String password1 =password.getText().toString();
                    for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String password2 = snapshot1.child("password").getValue().toString();
                        String role1 = snapshot1.child("role").getValue().toString();
                        if(role1.equals("ADMIN")&&password1.equals(password2)){
                            String user = snapshot1.child("userName").getValue().toString();
                            Toast.makeText(MainActivity.this,"Welcome Admin",Toast.LENGTH_LONG).show();
                            Intent intent1 = new Intent(MainActivity.this, AdministratorPage.class);
                            Bundle b1 = new Bundle();
                            b1.putString("userName",user);
                            b1.putString("role",role1);
                            intent1.putExtras(b1);
                            startActivity(intent1);
                            return;
                        }else if(role1.equals("Employee") && password1.equals(password2)){
                            String user = snapshot1.child("userName").getValue().toString();
                            Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_LONG).show();
                            Intent intent1 = new Intent(MainActivity.this, WelcomeScreen.class);
                            Bundle b1 = new Bundle();
                            b1.putString("userName", user);
                            b1.putString("role",role1);
                            intent1.putExtras(b1);
                            startActivity(intent1);
                            return;
                        } else if(role1.equals("Customer")&&password1.equals(password2)){
                            String user = snapshot1.child("userName").getValue().toString();
                            Toast.makeText(MainActivity.this,"Successful Customer Login",Toast.LENGTH_LONG).show();
                            Intent intent1 = new Intent(MainActivity.this, WelcomeScreen.class);
                            Bundle b1 = new Bundle();
                            b1.putString("userName",user);
                            b1.putString("role",role1);
                            intent1.putExtras(b1);
                            startActivity(intent1);
                            return;
                        }

                    }
                }
                Toast.makeText(MainActivity.this, "Invalid login info", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //TODO: Validate Login Fields
        //TODO: Check if it's in the database
        //TODO: If login is successful, pass info to login page and display role
        //TODO: If unsuccessful, display toaster with error message
    }
}
