package com.example.industrialcopera.Admin.editar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.industrialcopera.Administrador;
import com.example.industrialcopera.R;
import com.example.industrialcopera.clases.Concierto;
import com.example.industrialcopera.clases.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditarConcierto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarConcierto extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditarConcierto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditarConcierto.
     */
    // TODO: Rename and change types and number of parameters
    public static EditarConcierto newInstance(String param1, String param2) {
        EditarConcierto fragment = new EditarConcierto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_concierto, container, false);
    }

    Boolean editar=false;
    EditText et_artista,et_fecha,et_hora,et_precio,et_aforo;
    String artista,descripcion,fecha,hora;
    Double precio;
    int aforo;
    ImageView iv_foto;
    Button button;
    Administrador ma;
    Uri foto_url;
    private final static int SELECCIONAR_FOTO=1;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ma=(Administrador)getActivity();
        ma.fab.hide();

        et_artista=(EditText)view.findViewById(R.id.TI_E_Co_Artista);
//        FECHA Y HORA
        et_fecha=(EditText)view.findViewById(R.id.TI_E_Co_Fecha);
        et_hora=(EditText)view.findViewById(R.id.TI_E_Co_Hora);
//        FECHA Y HORA
        et_precio=(EditText)view.findViewById(R.id.TI_E_Co_Precio);
        et_aforo=(EditText)view.findViewById(R.id.TI_E_Co_Aforo);
        iv_foto=(ImageView)view.findViewById(R.id.IV_E_Co_Anadir);
        button=(Button)view.findViewById(R.id.B_E_Concierto);
        if(!ma.concierto.getArtista().equals("")){
            editar=true;
            et_artista.setText(ma.concierto.getArtista());
//        FECHA Y HORA
            et_fecha.setText(ma.concierto.getFecha());
            et_hora.setText(ma.concierto.getHora());
//        FECHA Y HORA
            et_precio.setText(ma.concierto.getPrecio()+"");
            et_aforo.setText(ma.concierto.getAforo()+"");
            button.setText("Actualizar");
        }

        et_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ma, "Calendario", Toast.LENGTH_SHORT).show();
                ma.mostrarCalendario();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });
        iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarFoto();
            }
        });

    }
    public void seleccionarFoto(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},1000);
        }
        Intent i = new Intent (Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, SELECCIONAR_FOTO);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK&&requestCode==SELECCIONAR_FOTO){
            foto_url=data.getData();
            iv_foto.setImageURI(foto_url);
            Toast.makeText(ma.context,"Imagen importada con exito",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ma.context,"Imagen NO importada",Toast.LENGTH_SHORT).show();
        }
    }

    public void enviar(){
        artista=et_artista.getText().toString();
//        FECHA Y HORA
        fecha=et_fecha.getText().toString();
        hora=et_hora.getText().toString();
//        FECHA Y HORA
        precio=Double.parseDouble(et_precio.getText().toString());
        aforo=Integer.parseInt(et_aforo.getText().toString());

        if(foto_url==null){
            Toast.makeText(ma.context, "Tiene que seleccionar una imagen", Toast.LENGTH_SHORT).show();
        }else
//            if(comprobar()) //Taqmbier comprobar valor unico
        {
            ma.ref.child("discoteca").child("concierto").orderByChild("artista").equalTo(artista)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean correcto=true;
                            if (!(editar&&ma.concierto.getArtista().equals(artista)
                                    &&ma.concierto.getFecha().equals(fecha))){
                                if(snapshot.hasChildren()){
    //                           COMPORBAR FECHA  (SOLO FECHA PARA QUE NO HAYA 2 DEL MISMO EL MISMO DIA)
                                    // TODO: 03/05/2021 hacer bien
                                    for(DataSnapshot hijo:snapshot.getChildren()){
                                        Concierto pojo_producto=hijo.getValue(Concierto.class);
                                        if(fecha.equals(pojo_producto.getFecha())){
                                            correcto=false;
                                        }
                                    }
                                }
                            }
                            if(correcto&&editar){
                                Concierto nuevo=new Concierto(artista,fecha,hora,descripcion,precio,ma.concierto.getVendidas(),aforo);
                                nuevo.setId(ma.concierto.getId());
                                ma.ref.child("discoteca").child("concierto").child(ma.concierto.getId()).setValue(nuevo);
                                if(foto_url!=null){
                                    ma.sto.child("tienda").child("eventos").child(ma.concierto.getId()).putFile(foto_url);
                                }
                                Toast.makeText(ma.context, "Concierto actualizado con exito", Toast.LENGTH_SHORT).show();
                                ma.concierto=new Concierto();
                                ma.navController.navigate(R.id.conciertos);

                            }else if(correcto){
                                Concierto nuevo=new Concierto(artista,fecha,hora,descripcion,precio,0,aforo);
                                String id=ma.ref.child("discoteca").child("concierto").push().getKey();
                                nuevo.setId(id);
                                ma.ref.child("discoteca").child("concierto").child(id).setValue(nuevo);
                                ma.sto.child("discoteca").child("concierto").child(id).putFile(foto_url);
                                Toast.makeText(ma.context, "Concierto añadido con exito", Toast.LENGTH_SHORT).show();
                                ma.navController.navigate(R.id.conciertos);
                            }else{
//                                et_nombre.setError(res.getString(R.string.error_evento_repetidos));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }
}