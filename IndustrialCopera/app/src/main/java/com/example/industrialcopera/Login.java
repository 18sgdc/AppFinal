package com.example.industrialcopera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.industrialcopera.clases.Usuario;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class Login extends AppCompatActivity {

//    EditText et_nombre,et_contra2
    EditText et_contra,et_correo;
    String correo, contra;

    Button b_inicio;
    DatabaseReference ref;
    StorageReference sto;
    boolean registrarse=false;

    SharedPreferences sp;
    SharedPreferences.Editor spE;
    long lastTime;
    long  ON_DAY_MS = 86400000;

    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciado();
        res=getResources();
//        et_nombre=(EditText) findViewById(R.id.et_inicio_usuario);
        et_contra=(EditText) findViewById(R.id.et_inicio_contra);
//        et_contra2=(EditText) findViewById(R.id.et_inicio_contra2);
        et_correo=(EditText) findViewById(R.id.et_inicio_email);
        b_inicio=(Button)findViewById(R.id.b_iniciar);

        ref= FirebaseDatabase.getInstance().getReference();
        sto= FirebaseStorage.getInstance().getReference();
        lastTime = sp.getLong("lastTime", -1);


    }

    public void entrar(View v){
        if(registrarse){
            Registrarse();
        }else{
            iniciar();
        }
    }

    public void iniciar(){
//        String usuario=et_nombre.getText().toString().trim();
        correo=et_correo.getText().toString().trim();
        contra=et_contra.getText().toString().trim();


        ref.child("discoteca").child("usuarios").orderByChild("correo").equalTo(correo)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){
                            DataSnapshot hijo=snapshot.getChildren().iterator().next();
//                            String id=hijo.getKey();
                            Usuario nuevo=hijo.getValue(Usuario.class);
//                            nuevo.setId(id);
                            if(nuevo.getContraseña().equals(contra)){
                                spE.putString("KEY",nuevo.getId());
                                spE.putString("USER",nuevo.getNombre());
                                spE.putBoolean("ADMIN",nuevo.isAdmin());
                                spE.commit();
                                Intent intent = null;
                                if(nuevo.isAdmin()){
                                    intent=new Intent(getApplicationContext(), Administrador.class);
                                }else{
                                    intent=new Intent(getApplicationContext(), ActivityUsuario.class);
                                }
                                startActivity(intent);
                            }else{
//                                et_nombre.setError(res.getString(R.string.error_inicio));
//                                et_contra.setError(res.getString(R.string.error_inicio));
                                // TODO: 23/04/2021 Completar los errores
                            }
                        }else{
//                            et_nombre.setError(res.getString(R.string.error_inicio));
//                            et_contra.setError(res.getString(R.string.error_inicio));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void Registrarse(){
//        final String nombre=et_nombre.getText().toString();
//        final String contra2=et_contra2.getText().toString();
        correo=et_correo.getText().toString();
        contra=et_contra.getText().toString();

        final AwesomeValidation mAwesomeValidation = new AwesomeValidation(BASIC);
        mAwesomeValidation.addValidation(
                this, R.id.et_inicio_email, android.util.Patterns.EMAIL_ADDRESS, R.string.error_correo);


//        if(nombre.equals("")){
//            et_nombre.setError(res.getString(R.string.vacio));
//        }else if(contra.equals("")){
//            et_contra.setError(res.getString(R.string.vacio));
//        }else if(contra2.equals("")){
//            et_contra2.setError(res.getString(R.string.vacio));
//        }else if(!contra.equals(contra2)) {
//            et_contra2.setError(res.getString(R.string.error_contra2));
//        }else{
        // TODO: 23/04/2021 Comprobaciones
            ref.child("discoteca").child("usuarios").orderByChild("correo").equalTo(correo)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChildren()){
                                et_correo.setError(res.getString(R.string.error_repetido_correo));
                            }else {
//                                Usuario nuevo=new Usuario(nombre,contra,correo,false,false);
                                Usuario nuevo=new Usuario();
//                                nuevo.setNombre(nombre);
                                nuevo.setCorreo(correo);
                                nuevo.setContraseña(contra);
                                String id=ref.child("discoteca").child("usuarios").push().getKey();
                                nuevo.setId(id);
                                ref.child("discoteca").child("usuarios").child(id).setValue(nuevo);
                                Uri foto_url= Uri.parse("R.drawable.user");


                                sto.child("discoteca").child("usuarios").child(id).putFile(foto_url);

                                spE.putString("KEY",nuevo.getId());
                                spE.putString("USER",nuevo.getNombre());
                                spE.putBoolean("ADMIN",nuevo.isAdmin());
                                spE.commit();
                                Intent intent;
                                if(nuevo.isAdmin()){
                                    intent=new Intent(getApplicationContext(), Administrador.class);
                                }else{
                                    intent=new Intent(getApplicationContext(), ActivityUsuario.class);
                                }
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

//        }
    }

    public void actualizar(View v){
        registrarse=!registrarse;
        if(registrarse){
//            ((TextInputLayout) findViewById(R.id.et_inicio_contra2G)).setVisibility(View.VISIBLE);
//            ((TextInputLayout) findViewById(R.id.et_inicio_emailG)).setVisibility(View.VISIBLE);
            b_inicio.setText(res.getString(R.string.registrarse));
            ((TextView)v).setText(res.getString(R.string.cambiarIniciar));
        }else{
//            ((TextInputLayout) findViewById(R.id.et_inicio_contra2G)).setVisibility(View.GONE);
//            ((TextInputLayout) findViewById(R.id.et_inicio_emailG)).setVisibility(View.GONE);
            b_inicio.setText(res.getString(R.string.iniciar_sesion));
            ((TextView)v).setText(res.getString(R.string.cambiarRegistrarse));
        }
    }

    public void iniciado(){
        sp=getSharedPreferences("discoteca",MODE_PRIVATE);
        spE=sp.edit();
        if(!sp.getString("KEY","").equals("")){
            if(!sp.getBoolean("ADMIN",true)){
                Intent intent=new Intent(getApplicationContext(), ActivityUsuario.class);
                startActivity(intent);
            }else{
                Intent intent=new Intent(getApplicationContext(),Administrador.class);
                startActivity(intent);
            }
        }
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