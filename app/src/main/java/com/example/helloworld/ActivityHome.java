package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhome);

        // Menghubungkan tombol dengan id
        Button btnWisata1 = findViewById(R.id.btnwisata1);
        Button btnWisata2 = findViewById(R.id.btnwisata2);
        Button btnWisata3 = findViewById(R.id.btnwisata3);
        Button btnWisata4 = findViewById(R.id.btnwisata4);
        Button btnWisata5 = findViewById(R.id.btnwisata5);

        // Menambahkan event listener untuk tombol Bali
        btnWisata1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, DetailWisataActivity.class);
                intent.putExtra("WISATA", "BALI");
                startActivity(intent);
            }
        });

        // Menambahkan event listener untuk tombol Sumatera Barat
        btnWisata2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, DetailWisataActivity.class);
                intent.putExtra("WISATA", "SUMATERA BARAT");
                startActivity(intent);
            }
        });

        // Menambahkan event listener untuk tombol Papua
        btnWisata3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, DetailWisataActivity.class);
                intent.putExtra("WISATA", "PAPUA");
                startActivity(intent);
            }
        });

        // Menambahkan event listener untuk tombol Sumatera Utara
        btnWisata4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, DetailWisataActivity.class);
                intent.putExtra("WISATA", "SUMATERA UTARA");
                startActivity(intent);
            }
        });

        // Menambahkan event listener untuk tombol Sulawesi Barat
        btnWisata5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, DetailWisataActivity.class);
                intent.putExtra("WISATA", "SULAWESI BARAT");
                startActivity(intent);
            }
        });
    }
}
