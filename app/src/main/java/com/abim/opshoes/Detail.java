package com.abim.opshoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class Detail extends AppCompatActivity {
    TextView tvNama, tvDeskripsi, tvHarga;
    ImageView imgSepatu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvNama = findViewById(R.id.tv_nama);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        tvHarga = findViewById(R.id.tv_hargatertulis);
        imgSepatu = findViewById(R.id.img_sepatu);


        Intent r = getIntent();
        String nama = r.getStringExtra("NAMA");
        String gambar = r.getStringExtra("GAMBAR");
        String deskripsi = r.getStringExtra("DESKRIPSI");
        String harga = r.getStringExtra("HARGA");


        tvNama.setText(nama);
        tvDeskripsi.setText(deskripsi);
        tvHarga.setText("Rp "+harga);
        Glide.with(this)
                .load(gambar)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .into(imgSepatu);

    }

    public void clickBeli(View view) {
        Intent i = new Intent(Detail.this, SuccessOrder.class);
        startActivity(i);
    }
}