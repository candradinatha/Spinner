package com.example.candradinatha.tugasspinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spPertandingan, spKelas;
    private EditText edtJumlahTiket;
    private Button btnPesan;
    
    private List<String> contentPertandingan, contentKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spPertandingan = (Spinner) findViewById(R.id.sp_pertandingan);
        spKelas = (Spinner) findViewById(R.id.sp_kelas);
        edtJumlahTiket = (EditText) findViewById(R.id.edt_jumlah_tiket);
        btnPesan = (Button) findViewById(R.id.btn_pesan);
        
        contentPertandingan = new ArrayList<String>();
        contentPertandingan.add("BUFC vs Persija");
        contentPertandingan.add("BUFC vs Sriwijaya FC");
        contentPertandingan.add("BUFC vs Arema");
        contentPertandingan.add("BUFC vs PSM");

        contentKelas = new ArrayList<String>();
        contentKelas.add("Regular");
        contentKelas.add("VIP");
        contentKelas.add("VVIP");

        //Buat adapter
        ArrayAdapter<String> contentPertantinganAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, contentPertandingan);
        ArrayAdapter<String> contentKelasAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, contentKelas);

        // Set adapter
        spPertandingan.setAdapter(contentPertantinganAdapter);
        spKelas.setAdapter(contentKelasAdapter);

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean valid = true;

                // Kondisi tdk valid
                if (edtJumlahTiket.getText().toString().isEmpty()) {
                    valid = false;
                    edtJumlahTiket.setError("Jumlah Tiket Masih Kosong");
                } else if (Integer.parseInt(edtJumlahTiket.getText().toString()) < 1) {
                    valid = false;
                    edtJumlahTiket.setError("Jumlah Tiket Minimal 1");
                }

                // Kondisi valid
                if (valid) {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);

                    intent.putExtra("pertandingan", spPertandingan.getSelectedItem().toString());
                    intent.putExtra("kelas", spKelas.getSelectedItem().toString());
                    intent.putExtra("jumlahTiket", edtJumlahTiket.getText().toString());

                    MainActivity.this.startActivity(intent);
                }

            }
        });

    }
}
