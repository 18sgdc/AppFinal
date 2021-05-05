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

import com.example.industrialcopera.ActivityUsuario;
import com.example.industrialcopera.Administrador;
import com.example.industrialcopera.R;
import com.example.industrialcopera.clases.Concierto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapConciertoAdmin extends RecyclerView.Adapter<AdapConciertoAdmin.Vh> {

    Context context;
    List<Concierto> conciertos;
    NavController navController;
    Administrador ma;

    public AdapConciertoAdmin(Administrador ma, List<Concierto> conciertos) {
        this.ma=ma;
        this.context = ma.context;
//        this.JuegosFiltrados = Juegos;
        this.conciertos = conciertos;
        this.navController = ma.navController;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.fila_admin_concierto, parent, false);
        return new Vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {

        final Concierto c = conciertos.get(position);
        holder.artista.setText(c.getArtista());
        holder.fecha.setText(c.getFecha());
        holder.vendidas.setText(c.getVendidas()+"");
        holder.aforo.setText(c.getAforo()+"");


        Picasso.get()
                .load(Uri.parse(c.getIdFotos()))
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
                ma.concierto=c;
                navController.navigate(R.id.editarConcierto);
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
        return conciertos.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        TextView artista, fecha, vendidas,aforo;
        ImageView imagen;
        Button b_info,b_edit;
//        CardView carta;

        public Vh(@NonNull View itemView) {
            super(itemView);
            artista = (TextView) itemView.findViewById(R.id.TV_FAC_Artista);
            fecha = (TextView) itemView.findViewById(R.id.TV_FAC_Fecha);
            vendidas = (TextView) itemView.findViewById(R.id.TV_FAC_Vendidas);
            aforo = (TextView) itemView.findViewById(R.id.TV_FAC_Aforo);
            imagen = (ImageView) itemView.findViewById(R.id.IV_FA_Concierto);
            b_info=(Button)itemView.findViewById(R.id.B_FAC_Info);
            b_edit=(Button)itemView.findViewById(R.id.B_FAC_Editar);
//            carta=(CardView) itemView.findViewById(R.id.cv_filaJ);
        }
    }
}
