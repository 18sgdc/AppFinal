package com.example.industrialcopera.Adaptadores;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.industrialcopera.Administrador;
import com.example.industrialcopera.R;
import com.example.industrialcopera.clases.Cupon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapCuponAdmin  extends RecyclerView.Adapter<AdapCuponAdmin.Vh> {

    Context context;
    List<Cupon> cupones;
    NavController navController;
    Administrador ma;

    public AdapCuponAdmin(Administrador ma, List<Cupon> cupones) {
        this.ma=ma;
        this.context = ma.context;
//        this.JuegosFiltrados = Juegos;
        this.cupones = cupones;
        this.navController = ma.navController;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.fila_admin_cupon, parent, false);
        return new Vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {

        final Cupon c = cupones.get(position);
        if (c.isConcierto()) {
            holder.tipo.setText("Descuento para conciertos");
        }else{
            holder.tipo.setText("Descuento para productos");
        }
        holder.precio.setText(c.getPrecio()+"€");
        if(c.isPorcentaje()){
            holder.valor.setText(c.getValor()+"%");
        }else{
            holder.valor.setText(c.getValor()+"€");
        }
        if (c.getMin()>0){
            holder.minimo.setText("Precio minimo"+c.getMin());
        }else{
            holder.minimo.setVisibility(View.GONE);
        }


        holder.b_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.b_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma.cupon=c;
                navController.navigate(R.id.editarCupon);
            }
        });

//        holder.carta.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ma.setJuego(j);
//                navController.navigate(R.id.verJuego);
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return cupones.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        TextView tipo, precio, valor,minimo;
        Button b_info,b_edit;
//        CardView carta;

        public Vh(@NonNull View itemView) {
            super(itemView);
            tipo = (TextView) itemView.findViewById(R.id.TV_FACu_Tipo);
            precio = (TextView) itemView.findViewById(R.id.TV_FACu_Precio);
            valor = (TextView) itemView.findViewById(R.id.TV_FACu_Valor);
            minimo = (TextView) itemView.findViewById(R.id.TV_FACu_Min);
            b_info=(Button)itemView.findViewById(R.id.B_FACu_Info);
            b_edit=(Button)itemView.findViewById(R.id.B_FACu_Editar);
//            carta=(CardView) itemView.findViewById(R.id.cv_filaJ);
        }
    }
}
