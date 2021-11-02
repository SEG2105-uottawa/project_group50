package com.example.byblos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.view.WindowManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity {

    TextView userName;
    TextView role;

    //Variables for animation
    Animation topAnim, bottomAnim;
    TextView welcome, roleMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        userName = (TextView) findViewById(R.id.usernameText);
        role = (TextView) findViewById(R.id.role);
        Bundle b = getIntent().getExtras();

        userName.setText(b.getString("userName")+ "!");
        role.setText(b.getString("role"));

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        welcome = findViewById(R.id.welcomeText);
        roleMsg = findViewById(R.id.roleMessage);

        welcome.setAnimation(topAnim);
        userName.setAnimation(topAnim);
        roleMsg.setAnimation(bottomAnim);
        role.setAnimation(bottomAnim);
    }
}
