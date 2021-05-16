package com.example.industrialcopera.User.VerObjeto;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.industrialcopera.ActivityUsuario;
import com.example.industrialcopera.R;
import com.example.industrialcopera.clases.VentaConcierto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerConcierto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerConcierto extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VerConcierto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerConcierto.
     */
    // TODO: Rename and change types and number of parameters
    public static VerConcierto newInstance(String param1, String param2) {
        VerConcierto fragment = new VerConcierto();
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
        return inflater.inflate(R.layout.fragment_ver_concierto, container, false);
    }

    ActivityUsuario ma;
    TextView artista,fecha,hora,precio;
    ImageView imagen;
    Button b;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ma=(ActivityUsuario)getActivity();
        artista=(TextView)view.findViewById(R.id.TV_VC_Artista);
        fecha=(TextView)view.findViewById(R.id.TV_VC_Fecha);
        hora=(TextView)view.findViewById(R.id.TV_VC_Hora);
        precio=(TextView)view.findViewById(R.id.TV_VC_Precio);
        imagen=(ImageView)view.findViewById(R.id.IV_VC_Foto);
        b=(Button)view.findViewById(R.id.B_VC_Comprar);

        artista.setText(ma.concierto.getArtista());
        fecha.setText(ma.concierto.getFecha());
        hora.setText(ma.concierto.getHora());
        precio.setText(ma.concierto.getPrecio()+"€");
        Glide.with(ma.context)
                .load(Uri.parse(ma.concierto.getIdFotos()))
                .into(imagen);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprar();
            }
        });

    }
    public void comprar(){
        Date seleccionada = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String hoy= sdf.format(seleccionada);

        VentaConcierto nuevo=new VentaConcierto(ma.concierto.getId(),"idusuario",hoy,ma.concierto.getPrecio(),ma.concierto.getPrecio());
        String id=ma.ref.child("discoteca").child("ventas_conciertos").push().getKey();
        nuevo.setId(id);
        ma.ref.child("discoteca").child("ventas_conciertos").child(id).setValue(nuevo);
        Toast.makeText(ma.context, "Entradas compradas con exito", Toast.LENGTH_SHORT).show();
        ma.navController.navigate(R.id.productos);
    }
}