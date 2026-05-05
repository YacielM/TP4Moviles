package com.example.tp4moviles.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp4moviles.R;
import com.example.tp4moviles.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.VH> {

    private List<Producto> items = new ArrayList<>();

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Producto p = items.get(position);
        holder.tvDescripcion.setText(p.getDescripcion());
        holder.tvCodigo.setText(p.getCodigo());
        holder.tvPrecio.setText(String.format("$ %.2f", p.getPrecio()));
    }

    @Override public int getItemCount() { return items.size(); }

    public void submitList(List<Producto> lista) {
        this.items = lista == null ? new ArrayList<>() : lista;
        notifyDataSetChanged();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvDescripcion, tvPrecio;
        VH(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}
