package com.example.industrialcopera;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.industrialcopera.General.Calendario;
import com.example.industrialcopera.User.Dialog.DialogComprarConcierto;
import com.example.industrialcopera.User.Dialog.DialogComprarProducto;
import com.example.industrialcopera.clases.Concierto;
import com.example.industrialcopera.clases.Cupon;
import com.example.industrialcopera.clases.Noticia;
import com.example.industrialcopera.clases.Producto;
import com.example.industrialcopera.clases.Usuario;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivityUsuario extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public NavController navController;
//    public FloatingActionButton fab;
    public Context context;
    public DatabaseReference ref;
    public StorageReference sto;
    SharedPreferences sp;
    SharedPreferences.Editor spE;
    public Usuario usuarioActual;
    public Concierto concierto;
    public Noticia noticia;
    public Producto producto;
    public Cupon cupon;
    public String idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        fab = findViewById(R.id.fab);
        context=getApplicationContext();
        ref= FirebaseDatabase.getInstance().getReference();
        sto= FirebaseStorage.getInstance().getReference();
        iniciado();
        concierto=new Concierto();
        noticia=new Noticia();
        producto=new Producto();
        cupon=new Cupon();


//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.main,
                R.id.copera,R.id.noticiasUser,R.id.conciertosUser,R.id.productosUser,R.id.cuponesUser,R.id.perfil)
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
//----------------------------------------------------------------------------------
//        PUEDE SER QUE HAYA QUE PONERLO EN EL CREATE OPTIONS MENU
//----------------------------------------------------------------------------------
        ref.child("discoteca").child("usuarios").orderByKey().equalTo(idCliente)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DataSnapshot hijo=snapshot.getChildren().iterator().next();
                        String id=hijo.getKey();
                        usuarioActual=hijo.getValue(Usuario.class);
                        usuarioActual.setId(id);
//                        tv_nombre=(TextView)findViewById(R.id.tv_min_nombre);
//                        tv_correo=(TextView)findViewById(R.id.tv_min_correo);
//                        iv_foto=(ImageView) findViewById(R.id.iv_min_foto);
//                        tv_nombre.setText(usuario.getNombre());
//                        tv_correo.setText(usuario.getCorreo());
//                        if(!usuario.isFoto()){
//                            iv_foto.setImageResource(R.drawable.user);
//                        }else{
//                            sto.child("tienda").child("usuarios")
//                                    .child(usuario.getId())
//                                    .getDownloadUrl()
//                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            Glide.with(c)
//                                                    .load(Uri.parse(uri.toString()))
//                                                    .into(iv_foto);
//                                        }
//                                    });
//                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.usuario, menu);
//        ref.child("discoteca").child("usuarios").orderByKey().equalTo(sp.getString("KEY",""))
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        DataSnapshot hijo=snapshot.getChildren().iterator().next();
//                        String id=hijo.getKey();
//                        usuarioActual=hijo.getValue(Usuario.class);
//                        usuarioActual.setId(id);
////                        tv_nombre=(TextView)findViewById(R.id.tv_min_nombre);
////                        tv_correo=(TextView)findViewById(R.id.tv_min_correo);
////                        iv_foto=(ImageView) findViewById(R.id.iv_min_foto);
////                        tv_nombre.setText(usuario.getNombre());
////                        tv_correo.setText(usuario.getCorreo());
////                        if(!usuario.isFoto()){
////                            iv_foto.setImageResource(R.drawable.user);
////                        }else{
////                            sto.child("tienda").child("usuarios")
////                                    .child(usuario.getId())
////                                    .getDownloadUrl()
////                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
////                                        @Override
////                                        public void onSuccess(Uri uri) {
////                                            Glide.with(c)
////                                                    .load(Uri.parse(uri.toString()))
////                                                    .into(iv_foto);
////                                        }
////                                    });
////                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public void salirUser (View v){
        SharedPreferences sp;
        SharedPreferences.Editor spE;

        sp=getSharedPreferences("tienda",MODE_PRIVATE);
        spE=sp.edit();

        spE.putString("KEY","");
        spE.putString("USER","");
        spE.putBoolean("ADMIN",false);
        spE.commit();
        Intent intent=new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }
    public void iniciado(){
        sp=getSharedPreferences("discoteca",MODE_PRIVATE);
        spE=sp.edit();
        idCliente=sp.getString("KEY","");
//        if(!sp.getString("KEY","").equals("")){
//            if(sp.getBoolean("ADMIN",false)){
//                Intent intent=new Intent(getApplicationContext(), Administrador.class);
//                startActivity(intent);
//            }
//        }else{
//            Intent intent=new Intent(getApplicationContext(), Login.class);
//            startActivity(intent);
//        }
    }

    public void comprarConcierto(){
        DialogComprarConcierto add=new DialogComprarConcierto();
        add.show(getSupportFragmentManager(), "Add");
    }


    public void comprarProducto(){
        DialogComprarProducto add=new DialogComprarProducto();
        add.show(getSupportFragmentManager(), "Add");
    }
}