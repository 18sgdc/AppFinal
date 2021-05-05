package com.example.industrialcopera.Adaptadores;

import android.content.Context;
import android.graphics.Color;
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

import com.example.industrialcopera.ActivityUsuario;
import com.example.industrialcopera.Administrador;
import com.example.industrialcopera.R;
import com.example.industrialcopera.clases.Producto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapProductoAdmin  extends RecyclerView.Adapter<AdapProductoAdmin.Vh> {

    Context context;
    List<Producto> productos;
    NavController navController;
    Administrador ma;

    public AdapProductoAdmin(Administrador ma, List<Producto> productos) {
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
        View v = li.inflate(R.layout.fila_admin_producto, parent, false);
        return new Vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {

        final Producto p = productos.get(position);
        holder.titulo.setText(p.getNombre());
//        holder.precio.setText(p.getPrecio()+" €");
        if(p.getStock()>0){
            holder.stock.setText(p.getStock()+"");
        }else{
            holder.stock.setText("Agotado");
            holder.stock.setTextColor(Color.RED);
        }


        Picasso.get()
                .load(Uri.parse(p.getUrlFoto()))
                .placeholder(R.drawable.spinner)
                .into(holder.imagen);

        holder.b_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.b_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ma.producto=p;
                navController.navigate(R.id.editarProducto);
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
        TextView titulo, precio, stock;
        ImageView imagen;
//        CardView carta;
        Button b_info,b_edit;

        public Vh(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.TV_FAP_Titulo);
//            precio = (TextView) itemView.findViewById(R.id.TV_FAP_);
            stock = (TextView) itemView.findViewById(R.id.TV_FAP_Stock);
            imagen = (ImageView) itemView.findViewById(R.id.IV_FA_Producto);
            b_edit=(Button)itemView.findViewById(R.id.B_FAP_Editar);
            b_info=(Button)itemView.findViewById(R.id.B_FAP_Info);
//            carta=(CardView) itemView.findViewById(R.id.cv_filaJ);
        }
    }
}
