package com.example.industrialcopera.General;

import android.widget.EditText;

public class metodos {

    static boolean validarVacio(EditText et){
        boolean correcto=true;
        if (et.getText().toString().equals("")){
            et.setError("No puedes dejar este campo vacio");
            correcto=false;
        }
        return correcto;
    }
    static boolean validarNum(EditText et){
        boolean correcto=true;
        if (Integer.parseInt(et.getText().toString())<=0){
            et.setError("Tiene que ser mayor que 0");
            correcto=false;
        }
        return correcto;
    }
    static boolean validarNum0(EditText et){
        boolean correcto=true;
        if (Integer.parseInt(et.getText().toString())<0){
            et.setError("Tiene que ser mayor o igual a 0");
            correcto=false;
        }
        return correcto;
    }
}
