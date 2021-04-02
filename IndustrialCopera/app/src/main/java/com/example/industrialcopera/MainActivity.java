package com.example.industrialcopera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

//    EditText et_nombre,et_contra,et_contra2,et_correo;
//    Button b_inicio;
//    DatabaseReference ref;
//    StorageReference sto;
//    boolean registrarse=false;
//
//    SharedPreferences sp;
//    SharedPreferences.Editor spE;
//    long lastTime;
//    long  ON_DAY_MS = 86400000;
//
//    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        iniciado();
//        res=getResources();
//        et_nombre=(EditText) findViewById(R.id.et_inicio_usuario);
//        et_contra=(EditText) findViewById(R.id.et_inicio_contra);
////        et_contra2=(EditText) findViewById(R.id.et_inicio_contra2);
////        et_correo=(EditText) findViewById(R.id.et_inicio_correo);
//        b_inicio=(Button)findViewById(R.id.b_iniciar);
//
//        ref= FirebaseDatabase.getInstance().getReference();
//        sto= FirebaseStorage.getInstance().getReference();
//        lastTime = sp.getLong("lastTime", -1);
//
//
//    }
//    public void iniciar(){
//        String usuario=et_nombre.getText().toString().trim();
//        final String contra=et_contra.getText().toString().trim();
//
//
//        ref.child("tienda").child("usuarios").orderByChild("nombre").equalTo(usuario)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.hasChildren()){
//                            DataSnapshot hijo=snapshot.getChildren().iterator().next();
//                            String id=hijo.getKey();
////                            Usuario nuevo=hijo.getValue(Usuario.class);
////                            nuevo.setId(id);
////                            if(nuevo.getContraseña().equals(contra)){
////                                spE.putString("KEY",nuevo.getId());
////                                spE.putString("USER",nuevo.getNombre());
////                                spE.putBoolean("ADMIN",nuevo.isAdmin());
////                                spE.commit();
////                                Intent intent = null;
////                                if(nuevo.isAdmin()){
////                                    intent=new Intent(getApplicationContext(), MainActivityAdmin.class);
////                                }else{
////                                    intent=new Intent(getApplicationContext(), MainActivityUsuario.class);
////                                }
////                                startActivity(intent);
////                            }else{
////                                et_nombre.setError(res.getString(R.string.error_inicio));
////                                et_contra.setError(res.getString(R.string.error_inicio));
////                            }
//                        }else{
////                            et_nombre.setError(res.getString(R.string.error_inicio));
////                            et_contra.setError(res.getString(R.string.error_inicio));
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//    }
//    public void iniciado(){
//        sp=getSharedPreferences("tienda",MODE_PRIVATE);
//        spE=sp.edit();
//        if(!sp.getString("KEY","").equals("")){
//            if(sp.getBoolean("ADMIN",true)){
//                Intent intent=new Intent(getApplicationContext(),Usuario.class);
//                startActivity(intent);
//            }else{
//                Intent intent=new Intent(getApplicationContext(),Administrador.class);
//                startActivity(intent);
//            }
//        }
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