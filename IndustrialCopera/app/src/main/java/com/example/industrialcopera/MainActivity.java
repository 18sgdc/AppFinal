package com.example.industrialcopera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void irUsuario(View v){
        Intent intent=new Intent(getApplicationContext(), Usuario.class);
        startActivity(intent);
    }
    public void irAdmin(View v){
        Intent intent=new Intent(getApplicationContext(), Administrador.class);
        startActivity(intent);
    }
}