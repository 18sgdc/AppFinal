package com.example.industrialcopera.User.VerObjeto;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.industrialcopera.ActivityUsuario;
import com.example.industrialcopera.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerProducto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerProducto extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VerProducto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerProducto.
     */
    // TODO: Rename and change types and number of parameters
    public static VerProducto newInstance(String param1, String param2) {
        VerProducto fragment = new VerProducto();
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
        return inflater.inflate(R.layout.fragment_ver_producto, container, false);
    }

    ActivityUsuario ma;
    ImageView imagen;
    TextView nombre, precio;
    EditText cantidad;
    Button b;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ma=(ActivityUsuario)getActivity();
        nombre=(TextView)view.findViewById(R.id.TV_VP_Nombre);
        precio=(TextView)view.findViewById(R.id.TV_VP_Precio);
        cantidad=(EditText) view.findViewById(R.id.TI_VP_Cantidad);
        imagen=(ImageView)view.findViewById(R.id.IV_VP_Foto);
        b=(Button)view.findViewById(R.id.B_VP_Comprar);


        nombre.setText(ma.concierto.getArtista());
        precio.setText(ma.producto.getPrecio()+"€");
        Glide.with(ma.context)
                .load(Uri.parse(ma.producto.getUrlFoto()))
                .into(imagen);

    }
}