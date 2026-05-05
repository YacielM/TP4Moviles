package com.example.tp4moviles.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp4moviles.R;
import com.example.tp4moviles.ui.adapters.ProductoAdapter;
import com.example.tp4moviles.viewmodel.MainViewModel;

public class ListaFragment extends Fragment {

    private MainViewModel vm;
    private ProductoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lista, container, false);

        RecyclerView rv = root.findViewById(R.id.recyclerProductos);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProductoAdapter();
        rv.setAdapter(adapter);

        vm = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        vm.getProductos().observe(getViewLifecycleOwner(), lista -> adapter.submitList(lista));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        // actualizar por si se agregaron productos desde CargarFragment
        vm.actualizarLista();
    }
}
