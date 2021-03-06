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
import com.example.industrialcopera.clases.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditarProducto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarProducto extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditarProducto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditarProducto.
     */
    // TODO: Rename and change types and number of parameters
    public static EditarProducto newInstance(String param1, String param2) {
        EditarProducto fragment = new EditarProducto();
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
        return inflater.inflate(R.layout.fragment_editar_producto, container, false);
    }

    Boolean editar;
    EditText et_titulo,et_descripcion,et_precio,et_stock;
    String titulo,descripcion;
    Double precio;
    int stock;
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

        et_titulo=(EditText)view.findViewById(R.id.TI_E_Pr_Titulo);
        et_descripcion=(EditText)view.findViewById(R.id.TI_E_Pr_Descripcion);
        et_precio=(EditText)view.findViewById(R.id.TI_E_Pr_Precio);
        et_stock=(EditText)view.findViewById(R.id.TI_E_Pr_Stock);
        iv_foto=(ImageView)view.findViewById(R.id.IV_E_pr_Anadir);
        button=(Button)view.findViewById(R.id.B_E_Producto);

        if(!ma.producto.getNombre().equals("")){
            editar=true;
            et_titulo.setText(ma.producto.getNombre());
            et_descripcion.setText(ma.producto.getDescripcion());
            et_precio.setText(ma.producto.getPrecio()+"");
            et_stock.setText(ma.producto.getStock()+"");
            button.setText("Actualizar");
        }


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
        titulo=et_titulo.getText().toString();
        descripcion=et_descripcion.getText().toString();
        precio=Double.parseDouble(et_precio.getText().toString());
        stock=Integer.parseInt(et_stock.getText().toString());

        if(foto_url==null){
            Toast.makeText(ma.context, "Tiene que seleccionar una imagen", Toast.LENGTH_SHORT).show();
        }else
//            if(comprobar()) //Taqmbier comprobar valor unico
            {
            ma.ref.child("concierto").child("productos").orderByChild("titulo").equalTo(titulo)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean correcto=true;
                            if(snapshot.hasChildren()&&!(titulo.equals(ma.producto.getNombre())&&editar)){
                                correcto=false;
//                                for(DataSnapshot hijo:snapshot.getChildren()){
//                                    Producto pojo_producto=hijo.getValue(Producto.class);
//                                    if(pojo_evento.getFecha().equals(fechaBien)){
//                                        correcto=false;
//                                    }
//                                }
                            }
                            if(correcto){
                                Producto nuevo=new Producto(titulo,descripcion,precio,stock);
                                String id=ma.ref.child("discoteca").child("productos").push().getKey();
                                nuevo.setId(id);
                                ma.sto.child("discoteca").child("productos").child(id).putFile(foto_url);
                                ma.ref.child("discoteca").child("productos").child(id).setValue(nuevo);
                                Toast.makeText(ma.context, "Producto a??adido con exito", Toast.LENGTH_SHORT).show();
//                                NavController navController = Navigation.findNavController(ma, R.id.nav_host_fragment_admin);
                                ma.navController.navigate(R.id.productos);
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