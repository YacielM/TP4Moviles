package com.example.tp4moviles.data;

import com.example.tp4moviles.model.Producto;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogHelper {
    public static List<Producto> filtrarPorDescripcion(List<Producto> lista, String q) {
        if (q == null || q.trim().isEmpty()) return lista;
        String lower = q.trim().toLowerCase();
        return lista.stream()
                .filter(p -> p.getDescripcion().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }
}
