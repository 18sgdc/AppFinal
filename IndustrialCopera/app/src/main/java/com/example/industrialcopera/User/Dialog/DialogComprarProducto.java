package com.example.industrialcopera.User.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.industrialcopera.ActivityUsuario;
import com.example.industrialcopera.R;
import com.example.industrialcopera.clases.Concierto;
import com.example.industrialcopera.clases.Cupon;
import com.example.industrialcopera.clases.CuponComprado;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link DialogComprarProducto#newInstance} factory method to
// * create an instance of this fragment.
// */
public class DialogComprarProducto extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DialogComprarProducto() {
        // Required empty public constructor
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogo();
    }


    Button bMas,bMenos,bComprar,bCarrito;
    TextView titulo,cantidad,precioBase,precioCupon, tv_precioCupon, tituloSpinner;
    Spinner cupon;
    ActivityUsuario ma;
    ArrayList<CuponComprado> cupones;
    int cuponSelect;

    private Dialog crearDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialog_comprar_producto, null);
        builder.setView(v);
        ma=(ActivityUsuario) getActivity();
        cupones=new ArrayList<>();


        bMas=(Button)v.findViewById(R.id.B_DP_Mas);
        bMenos=(Button)v.findViewById(R.id.B_DP_Menos);
        bComprar=(Button)v.findViewById(R.id.B_DP_Comprar);
        bCarrito=(Button)v.findViewById(R.id.B_DP_Carrito);

        titulo=(TextView) v.findViewById(R.id.TV_DP_Titulo);
        cantidad=(TextView) v.findViewById(R.id.TV_DP_Cantidad);
        precioBase=(TextView) v.findViewById(R.id.TV_DP_PrecioO);
        precioCupon=(TextView) v.findViewById(R.id.TV_DP_PrecioC);
        tv_precioCupon=(TextView) v.findViewById(R.id.TV_DP_Titulo_PrecioC);
        tituloSpinner=(TextView) v.findViewById(R.id.TV_DP_Cupon);
        cupon=(Spinner) v.findViewById(R.id.S_DP_Cupon);

        titulo.setText("Comprar "+ma.producto.getNombre());
//        precioBase.setText(ma.producto.getPrecio()+"€");
//        precioCupon.setText(ma.producto.getPrecio()+"€");

        ma.ref.child("discoteca").child("cupones_comprados").orderByChild("idCliente").equalTo(ma.idCliente)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        cupones.clear();
                        cupones.add(new CuponComprado("","",false,false,-1,-1));
                        for(DataSnapshot hijo:snapshot.getChildren()){
                            CuponComprado pojo_cupon_comprado=hijo.getValue(CuponComprado.class);
                            if(!pojo_cupon_comprado.isConcierto()&&
                                    pojo_cupon_comprado.getMin()>=0){
                                cupones.add(pojo_cupon_comprado);
                            }
                        }

                        if(cupones.size()<2){
                            precioCupon.setVisibility(View.GONE);
                            tv_precioCupon.setVisibility(View.GONE);
                            tituloSpinner.setVisibility(View.GONE);
                            cupon.setVisibility(View.GONE);
                        }else{
                            setSpinner();
                            actualizarPrecios();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



        bMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidad.setText(Integer.parseInt(cantidad.getText().toString())+1+"");
                actualizarPrecios();
            }
        });
        bMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(cantidad.getText().toString())>1){
                    cantidad.setText(Integer.parseInt(cantidad.getText().toString())-1+"");
                    actualizarPrecios();
                }
            }
        });

        bComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ma, "Comprado", Toast.LENGTH_SHORT).show();
            }
        });
        bCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ma, "Carrito", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }
    public void setSpinner(){

        ArrayAdapter<CuponComprado> pa = new ArrayAdapter<CuponComprado>(
                ma.context ,
                android.R.layout.simple_list_item_1, cupones);
        cupon.setAdapter(pa);

        cupon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cuponSelect=position;
                actualizarPrecios();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public void actualizarPrecios(){
        double precio=ma.producto.getPrecio()*Integer.parseInt(cantidad.getText().toString());
        precioBase.setText(precio+"€");
//        int cuponSelect=0;
        precioCupon.setText(cuponSelect+"€ xxx");
        if(cuponSelect==0) {
//            tituloSpinner.setText();
            precioCupon.setText(precio+"€");
        }else{
            if(cupones.get(cuponSelect).isPorcentaje()){
                precioCupon.setText(precio*(100-cupones.get(cuponSelect).getValue())/100+"€");
            }else{
                precioCupon.setText(precio-cupones.get(cuponSelect).getValue()+"€");
            }
        }
    }



    Activity activity;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.activity= (Activity) context;
        }else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_comprar_producto, container, false);
    }
}