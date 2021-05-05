package com.example.industrialcopera.Adaptadores;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.industrialcopera.ActivityUsuario;
import com.example.industrialcopera.R;
import com.example.industrialcopera.clases.Producto;
import com.example.industrialcopera.clases.Producto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapProductoUser extends RecyclerView.Adapter<AdapProductoUser.Vh> {

    Context context;
    List<Producto> productos;
    NavController navController;
    ActivityUsuario ma;

    public AdapProductoUser(ActivityUsuario ma, List<Producto> productos) {
        this.ma=ma;
        this.context = ma.context;
//        this.JuegosFiltrados = Juegos;
        this.productos = productos;
        this.navController = ma.navController;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.fila_producto, parent, false);
        return new Vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapProductoUser.Vh holder, int position) {

        final Producto p = productos.get(position);
        holder.titulo.setText(p.getNombre());
        holder.precio.setText(p.getPrecio()+" €");
        if(p.getStock()>0){
            holder.agotado.setVisibility(View.GONE);
        }else{
            holder.agotado.setVisibility(View.VISIBLE);
        }


        Picasso.get()
                .load(Uri.parse(p.getUrlFoto()))
                .placeholder(R.drawable.spinner)
                .into(holder.imagen);
        holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma.producto=p;
                navController.navigate(R.id.verProducto);
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
        return productos.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        TextView titulo, precio, agotado;
        ImageView imagen;
        ConstraintLayout cl;
//        CardView carta;

        public Vh(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.TV_VF_Producto_Titulo);
            precio = (TextView) itemView.findViewById(R.id.TV_VF_Producto_Precio);
            agotado = (TextView) itemView.findViewById(R.id.TV_VF_Producto_Agotado);
            imagen = (ImageView) itemView.findViewById(R.id.IV_VF_Producto);
//            carta=(CardView) itemView.findViewById(R.id.cv_filaJ);
            cl=(ConstraintLayout)itemView.findViewById(R.id.CL_Fila_Producto);
        }
    }
}