package com.example.industrialcopera.Adaptadores;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.industrialcopera.ActivityUsuario;
import com.example.industrialcopera.R;
import com.example.industrialcopera.clases.Concierto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapConciertoUser extends RecyclerView.Adapter<AdapConciertoUser.Vh> {

    Context context;
    List<Concierto> conciertos;
    NavController navController;
    ActivityUsuario ma;

    public AdapConciertoUser(ActivityUsuario ma, List<Concierto> conciertos) {
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
        View v = li.inflate(R.layout.fila_concierto_foto, parent, false);
        return new Vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {

        final Concierto c = conciertos.get(position);
        holder.nombre.setText(c.getArtista());
        holder.fecha.setText(c.getFecha());


        Picasso.get()
                .load(Uri.parse(c.getIdFotos()))
                .placeholder(R.drawable.spinner)
                .into(holder.imagen);

        holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma.concierto=c;
                navController.navigate(R.id.verConcierto);
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
        TextView nombre, fecha;
        ImageView imagen;
        ConstraintLayout cl;
//        CardView carta;

        public Vh(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.TV_VF_Concierto_Artista);
            fecha = (TextView) itemView.findViewById(R.id.TV_VF_Concierto_Fecha);
            imagen = (ImageView) itemView.findViewById(R.id.IV_VF_Concierto);
//            carta=(CardView) itemView.findViewById(R.id.cv_filaJ);
            cl=(ConstraintLayout)itemView.findViewById(R.id.CL_Fila_Concierto_foto);
        }
    }
}
