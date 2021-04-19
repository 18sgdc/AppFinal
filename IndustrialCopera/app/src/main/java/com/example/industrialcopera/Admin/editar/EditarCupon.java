package com.example.industrialcopera.Admin.editar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.industrialcopera.Administrador;
import com.example.industrialcopera.R;
import com.example.industrialcopera.clases.Cupon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditarCupon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarCupon extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditarCupon() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditarCupon.
     */
    // TODO: Rename and change types and number of parameters
    public static EditarCupon newInstance(String param1, String param2) {
        EditarCupon fragment = new EditarCupon();
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
        return inflater.inflate(R.layout.fragment_editar_cupon, container, false);
    }

    EditText et_precio,et_stock,et_valor, et_precio_min;
    Switch sw_concierto, sw_porcentaje;
//    String artista,descripcion;
    Boolean concierto,porcentaje;
    Double valor,precio_min;
    int precio,stock;
    Button button;
    Administrador ma;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ma=(Administrador)getActivity();
        ma.fab.hide();


        et_precio=(EditText)view.findViewById(R.id.TI_E_Cu_Precio);
        et_stock=(EditText)view.findViewById(R.id.TI_E_Cu_Stock);
        et_precio_min=(EditText)view.findViewById(R.id.TI_E_Cu_Precio_Min);
        et_valor=(EditText)view.findViewById(R.id.TI_E_Cu_Valor);
        sw_concierto=(Switch)view.findViewById(R.id.SW_E_Cu_Concierto);
        sw_porcentaje=(Switch)view.findViewById(R.id.SW_E_Cu_Valor);
        button=(Button)view.findViewById(R.id.B_E_Concierto);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });


    }

    public void enviar(){
        precio=Integer.parseInt(et_precio.getText().toString());
        stock=Integer.parseInt(et_stock.getText().toString());
        valor=Double.parseDouble(et_valor.getText().toString());
        precio_min=Double.parseDouble(et_precio_min.getText().toString());


//            if(comprobar()) //Taqmbier comprobar valor unico
//        {
            ma.ref.child("discoteca").child("cupon")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Cupon nuevo=new Cupon(concierto,precio,stock,porcentaje,valor,precio_min);
                            String id=ma.ref.child("discoteca").child("cupon").push().getKey();
                            nuevo.setId(id);
                            ma.ref.child("discoteca").child("cupon").child(id).setValue(nuevo);
                            Toast.makeText(ma.context, "Cupon añadido con exito", Toast.LENGTH_SHORT).show();
                            ma.navController.navigate(R.id.conciertos);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
//        }
    }
}