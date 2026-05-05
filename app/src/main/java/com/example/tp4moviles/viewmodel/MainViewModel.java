package com.example.tp4moviles.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp4moviles.MainActivity;
import com.example.tp4moviles.model.Producto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Producto>> productosLive = new MutableLiveData<>();

    public MainViewModel(){
        actualizarLista();
    }

    public MutableLiveData<List<Producto>> getProductos() {
        return productosLive;
    }
    public void actualizarLista() {
        List<Producto> copia = new ArrayList<>(MainActivity.productos);
        copia.sort(Comparator.comparing(Producto::getDescripcion, String.CASE_INSENSITIVE_ORDER));
        productosLive.setValue(copia);
    }
}
