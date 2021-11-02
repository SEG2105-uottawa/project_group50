package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;


public class AdministratorPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_page);
        getSupportActionBar().hide();
    }

    public void createServiceBtn(View view){
        Intent intent = new Intent(AdministratorPage.this, CreateService.class);
        startActivity(intent);
    }
    public void editServiceBtn(View view){
        Intent intent = new Intent(AdministratorPage.this, EditService.class);
        startActivity(intent);
    }
    public void deletingService(View view){
        Intent intent = new Intent(AdministratorPage.this, DeletingService.class);
        startActivity(intent);
    }
    /*
    public void deleteUserBtn(View view){
        Intent intent = new Intent(AdministratorPage.this, DeleteUser.class);
        startActivity(intent);
    }*/



}