package com.example.candradinatha.tugasspinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    private TextView tvPertandingan, tvKelas, tvSubTotal, tvJumlahTiket, tvHargaTiket, tvTotalHarga;
    private Button btnBayar;
    String mataUang = "Rp. ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvPertandingan = (TextView) findViewById(R.id.tv_pertandingan);
        tvKelas = (TextView) findViewById(R.id.tv_kelas);
        tvSubTotal = (TextView) findViewById(R.id.tv_sub_total);
        tvJumlahTiket = (TextView) findViewById(R.id.tv_jumlah_tiket);
        tvHargaTiket = (TextView) findViewById(R.id.tv_harga_tiket);
        tvTotalHarga = (TextView) findViewById(R.id.tv_total);

        btnBayar = (Button) findViewById(R.id.btn_bayar);

        final Intent intent = getIntent();

        // pertandingan
        final String pertandingan = intent.getStringExtra("pertandingan");
        tvPertandingan.setText(pertandingan);

        // kelas
        final String kelas = intent.getStringExtra("kelas");
        tvKelas.setText(kelas);

        // harga per tiket
        int hargaTiket = getClassPrice(kelas);
        tvHargaTiket.setText(mataUang + convertMoney(hargaTiket));

        // jumlah tiket
        final String jumlah = intent.getStringExtra("jumlahTiket");
        tvJumlahTiket.setText(jumlah);

        int subTotal = hargaTiket * Integer.valueOf(jumlah);
        tvSubTotal.setText(mataUang + convertMoney(subTotal));
        tvTotalHarga.setText(mataUang + convertMoney(subTotal));

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ResultActivity.this, "Maaf, Opsi Terkait Belum Tersedia ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public int getClassPrice (String kelas) {
        int price = 0;
        switch (kelas) {
            case "Regular":
                price = 75000;
                break;
            case "VIP":
                price = 150000;
                break;
            case "VVIP":
                price = 300000;
                break;
            default:
                price = 0;
                break;
        }
        return price;
    }

    public String convertMoney (int money) {
        return NumberFormat.getNumberInstance(Locale.GERMANY).format(money);
    }
}
