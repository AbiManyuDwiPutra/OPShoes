package com.abim.opshoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
public class Adapter extends RecyclerView.Adapter<Adapter.ListViewHolder> {
    private ArrayList<Sepatu> listSepatu;
    private OnItemClickCallback onItemClickCallback;
    Context context;
    private String id_user;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public Adapter(Context context, ArrayList<Sepatu> list) {
        this.listSepatu = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sepatu, parent, false);
        return new ListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Sepatu shoes = listSepatu.get(position);
        String nama = shoes.getNama();
        String deskripsi = shoes.getDeskripsi();
        String gambar = shoes.getGambar();
        int id = shoes.getId();
        int harga = shoes.getHarga();
        int terjual = shoes.getTerjual();


        holder.tvNama.setText(nama);
        com.bumptech.glide.Glide.with(context)
                .load(gambar)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .into(holder.imgSepatu);
        holder.tvDeskripsi.setText(deskripsi);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listSepatu.get(holder.getAdapterPosition()));
            }
        });
    }
    @Override
    public int getItemCount() {
        return listSepatu.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSepatu;
        TextView tvNama, tvDeskripsi;

        public ListViewHolder(View itemView) {
            super(itemView);
            imgSepatu = itemView.findViewById(R.id.img_item);
            tvNama = itemView.findViewById(R.id.tv_item);
            tvDeskripsi = itemView.findViewById(R.id.tv_detail);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Sepatu shoes);
    }
}
