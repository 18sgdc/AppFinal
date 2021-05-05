package com.example.industrialcopera.Adaptadores;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.industrialcopera.ActivityUsuario;
import com.example.industrialcopera.R;
import com.example.industrialcopera.clases.Concierto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapConciertoFilaUser  extends RecyclerView.Adapter<AdapConciertoFilaUser.Vh> {

    Context context;
    List<Concierto> conciertos;
    NavController navController;
    ActivityUsuario ma;

    public AdapConciertoFilaUser(ActivityUsuario ma, List<Concierto> conciertos) {
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
        View v = li.inflate(R.layout.fila_noticia, parent, false);
        return new Vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {

        final Concierto c = conciertos.get(position);
        holder.titulo.setText(c.getArtista());
        if(c.getPrecio()==0){
            holder.precio.setText("gratuito");
            holder.precio.setTextColor(ma.getResources().getColor(R.color.color2));
        }else{
            holder.precio.setText(c.getPrecio()+" €");
        }
//        if(c.getAforo()-c.getVendidas()>0){
//            holder.agotado.setVisibility(View.VISIBLE);
//        }else{
//            holder.agotado.setVisibility(View.GONE);
//        }


        Picasso.get()
                .load(Uri.parse(c.getIdFotos()))
                .placeholder(R.drawable.spinner)
                .into(holder.imagen);

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
        TextView titulo, precio, fecha;
        ImageView imagen;
//        CardView carta;

        public Vh(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.TV_VF_Noticia_Artista);
            precio = (TextView) itemView.findViewById(R.id.TV_VF_Noticia_Precio);
            fecha = (TextView) itemView.findViewById(R.id.TV_VF_Noticia_Fecha);
            imagen = (ImageView) itemView.findViewById(R.id.IV_VF_Concierto);
//            carta=(CardView) itemView.findViewById(R.id.cv_filaJ);
        }
    }
}
