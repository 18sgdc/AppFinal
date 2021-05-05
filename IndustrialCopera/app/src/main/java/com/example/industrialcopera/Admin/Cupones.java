package com.example.industrialcopera.Admin;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.industrialcopera.Adaptadores.AdapConciertoAdmin;
import com.example.industrialcopera.Adaptadores.AdapCuponAdmin;
import com.example.industrialcopera.Administrador;
import com.example.industrialcopera.R;
import com.example.industrialcopera.clases.Concierto;
import com.example.industrialcopera.clases.Cupon;
import com.example.industrialcopera.clases.Producto;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cupones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cupones extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cupones() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cupones.
     */
    // TODO: Rename and change types and number of parameters
    public static Cupones newInstance(String param1, String param2) {
        Cupones fragment = new Cupones();
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
        return inflater.inflate(R.layout.fragment_cupones, container, false);
    }


    Administrador ma;
    List<Cupon> cupones;
    RecyclerView rv;
    AdapCuponAdmin adaptador;
    RecyclerView.LayoutManager rvL;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ma=(Administrador)getActivity();

        ma.fab.show();
        ma.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma.navController.navigate(R.id.editarCupon);
                ma.producto=new Producto();
            }
        });



        rv = (RecyclerView) view.findViewById(R.id.RV_Admin_Cupones);
        cupones = new ArrayList<Cupon>();


//        rvL = new StaggeredGridLayoutManager(2, 1);
        rvL=new LinearLayoutManager(ma.context);
        rv.setLayoutManager(rvL);
        adaptador = new AdapCuponAdmin(ma, cupones);
        rv.setAdapter(adaptador);
        ma.ref.child("discoteca").child("cupon").orderByChild("fecha")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        cupones.clear();
                        for (DataSnapshot hijo : snapshot.getChildren()) {
                            Cupon pojo_cupon = hijo.getValue(Cupon.class);
//                            if(pojo_juego.getStock()>0&& Modulos.getPrecioBienN(ma,pojo_juego.getPrecio())>min
//                                    &&(Modulos.getPrecioBienN(ma,pojo_juego.getPrecio())<max||max==maximo)){
//                                if(cat_actual==0){
//                                    pojo_juego.setId(hijo.getKey());
                            cupones.add(pojo_cupon);
//                                }else{
//                                    if(pojo_juego.getCategoría().equals(categorias.get(cat_actual))){
//                                        pojo_juego.setId(hijo.getKey());
//                                        juegos.add(pojo_juego);
//                                    }
//                                }
//                            }
                        }

                        if (cupones.isEmpty()) {
                            Toast.makeText(ma.context, "No hay conciertos", Toast.LENGTH_SHORT).show();
                            adaptador.notifyDataSetChanged();
                        } else {
                            adaptador.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}