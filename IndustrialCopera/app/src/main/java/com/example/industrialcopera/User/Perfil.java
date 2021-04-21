package com.example.industrialcopera.User;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.industrialcopera.ActivityUsuario;
import com.example.industrialcopera.R;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Perfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil newInstance(String param1, String param2) {
        Perfil fragment = new Perfil();
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
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    ActivityUsuario ma;
    TextView tv_nombre,tv_correo,tv_direccion;
    ImageView iv_editar,iv_cancelar,iv_foto,iv_eliminar;
    EditText et_nombre,et_correo,et_contra1,et_contra2,et_direccion;
    Button b_actualizar;
    ConstraintLayout cl_ver,cl_editar;
    Uri foto_url;
    private final static int SELECCIONAR_FOTO=1;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ma=(ActivityUsuario)getActivity();

        tv_nombre=(TextView)view.findViewById(R.id.TV_Nombre);
        tv_correo=(TextView)view.findViewById(R.id.TV_Correo);
        tv_direccion=(TextView)view.findViewById(R.id.TV_Direccion);
        iv_foto=(ImageView)view.findViewById(R.id.IV_E_Foto_Mis_datos);
        iv_eliminar=(ImageView)view.findViewById(R.id.IV_E_Mi_Foto);

        et_nombre=(EditText) view.findViewById(R.id.TI_E_Da_Nombre);
        et_correo=(EditText) view.findViewById(R.id.TI_E_Da_Correo);
        et_contra1=(EditText) view.findViewById(R.id.TI_E_Da_Contra);
        et_contra2=(EditText) view.findViewById(R.id.TI_E_Da_Contra2);
        et_direccion=(EditText) view.findViewById(R.id.TI_E_Da_Direccion);
        b_actualizar=(Button)view.findViewById(R.id.B_A_Mis_Datos);


        iv_editar=(ImageView)view.findViewById(R.id.IV_E_Mis_Datos);
        iv_cancelar=(ImageView)view.findViewById(R.id.IV_C_Mis_Datos);
        cl_ver=(ConstraintLayout)view.findViewById(R.id.CL_V_Mis_Datos);
        cl_editar=(ConstraintLayout)view.findViewById(R.id.CL_E_Mis_Datos);

        ma.sto.child("discoteca").child("usuarios")
                .child(ma.usuarioActual.getId())
                .getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        ma.usuarioActual.setUrlFoto(uri.toString());
                        if (!ma.usuarioActual.isFoto()){
                            iv_eliminar.setVisibility(View.GONE);
                            iv_foto.setImageResource(R.drawable.user);
                        }else{
                            Glide.with(ma.context)
                                    .load(Uri.parse(ma.usuarioActual.getUrlFoto()))
                                    .into(iv_foto);

                        }
                    }
                });

        settear();

        iv_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarEditar();
            }
        });
        iv_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cl_editar.setVisibility(View.GONE);
                cl_ver.setVisibility(View.VISIBLE);
            }
        });
        b_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });
        iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarFoto();
            }
        });
    }
    public void settear(){
        cl_editar.setVisibility(View.GONE);
        cl_ver.setVisibility(View.VISIBLE);
        tv_nombre.setText(ma.usuarioActual.getNombre());
        tv_correo.setText(ma.usuarioActual.getCorreo());
        if(!ma.usuarioActual.getDireccion().equals("")){
            tv_direccion.setText(ma.usuarioActual.getDireccion());
        }

    }
    public void pasarEditar(){
        cl_ver.setVisibility(View.GONE);
        cl_editar.setVisibility(View.VISIBLE);
        et_nombre.setText(ma.usuarioActual.getNombre());
        et_correo.setText(ma.usuarioActual.getCorreo());
        if(!ma.usuarioActual.getDireccion().equals("")){
            et_direccion.setText(ma.usuarioActual.getDireccion());
        }

    }
    public void actualizar(){
        if(!et_correo.getText().toString().equals(ma.usuarioActual.getCorreo())){
            ma.usuarioActual.setCorreo(et_correo.getText().toString());
        }
        if(!et_contra1.getText().toString().equals("")){
            ma.usuarioActual.setContraseña(et_contra1.getText().toString());
        }
        ma.usuarioActual.setDireccion(et_direccion.getText().toString());
        ma.ref.child("discoteca").child("usuarios").child(ma.usuarioActual.getId()).setValue(ma.usuarioActual);
        settear();
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
            ma.sto.child("discoteca").child("usuarios").child(ma.usuarioActual.getId()).putFile(foto_url);
            ma.usuarioActual.setFoto(true);
            ma.ref.child("discoteca").child("usuarios").child(ma.usuarioActual.getId()).child("foto").setValue(true);
            iv_eliminar.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(ma.context,"Imagen NO importada",Toast.LENGTH_SHORT).show();
        }
    }

}