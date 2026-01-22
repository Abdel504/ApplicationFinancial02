package com.example.applicatie_financial_fail_252;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus, tvDetail, tvFooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Verbindt de tekstvelden met de layout
        tvStatus = findViewById(R.id.tvStatus);
        tvDetail = findViewById(R.id.tvDetail);
        tvFooter = findViewById(R.id.tvFooter);

        // Simuleert een scan maar zonder echte detectie
        tvStatus.setText("Scan voltooid");
        tvDetail.setText("Geen SIM detectiefunctie gevonden.");
        tvFooter.setText("Controle afgerond (fail versie)");
    }
}
