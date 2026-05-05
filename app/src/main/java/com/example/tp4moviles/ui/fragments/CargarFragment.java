package com.example.tp4moviles.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tp4moviles.MainActivity;
import com.example.tp4moviles.R;
import com.example.tp4moviles.model.Producto;

public class CargarFragment extends Fragment {

    private EditText etCodigo, etDescripcion, etPrecio;
    private Button btnAgregar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cargar, container, false);

        etCodigo = root.findViewById(R.id.etCodigo);
        etDescripcion = root.findViewById(R.id.etDescripcion);
        etPrecio = root.findViewById(R.id.etPrecio);
        btnAgregar = root.findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(v -> agregarProducto());

        return root;
    }

    private void agregarProducto() {
        String codigo = etCodigo.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String precioStr = etPrecio.getText().toString().trim();

        if (codigo.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
            Toast.makeText(getContext(), "Completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Precio inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        // validar código único
        for (Producto p : MainActivity.productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                Toast.makeText(getContext(), "Código ya existe", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Producto nuevo = new Producto(codigo, descripcion, precio);
        MainActivity.productos.add(nuevo);
        Toast.makeText(getContext(), "Producto agregado", Toast.LENGTH_SHORT).show();

        // limpiar campos
        etCodigo.setText("");
        etDescripcion.setText("");
        etPrecio.setText("");
    }
}
