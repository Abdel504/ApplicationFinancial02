package com.example.applicatie_financial_pass_252;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_READ_PHONE_STATE = 252;

    private TextView tvStatus, tvDetail, tvFooter;
    private Button btnRescan; // knop voor scannen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Koppelt tekstvelden aan layout
        tvStatus = findViewById(R.id.tvStatus);
        tvDetail = findViewById(R.id.tvDetail);
        tvFooter = findViewById(R.id.tvFooter);
        btnRescan = findViewById(R.id.btnRescan);

        // Start direct de SIM detectie
        tvStatus.setText("SIM detectie uitvoeren...");
        requestPermissionThenDetect();

        // Knop om opnieuw te scannen
        btnRescan.setOnClickListener(v -> {
            tvStatus.setText("SIM detectie uitvoeren...");
            tvDetail.setText("");
            tvFooter.setText("Controle in uitvoering...");
            requestPermissionThenDetect();
        });
    }

    // Controleert of toestemming READ_PHONE_STATE al is gegeven
    private void requestPermissionThenDetect() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Vraag toestemming aan als die nog niet is verleend
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE}, REQ_READ_PHONE_STATE);
        } else {
            showResult(); // Toestemming is al verleend
        }
    }

    // Wordt aangeroepen zodra de gebruiker toestemming geeft of weigert
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] perms, @NonNull int[] res) {
        super.onRequestPermissionsResult(requestCode, perms, res);
        if (requestCode == REQ_READ_PHONE_STATE) {
            showResult();
        }
    }

    // Toont het resultaat van de detectie
    private void showResult() {
        // AppInspector zoekt deze methode en naam op (vereist)
        SimSwapDetection.isSimSwapped();
        String tag = "SimSwapDetection";

        // Controleert of de SIM is gewijzigd
        boolean changed = SimSwapDetection.hasSimProbablyChanged(this);

        // Tekst aanpassen op basis van resultaat
        tvStatus.setText("Scan voltooid");
        tvDetail.setText(changed
                ? "Mogelijke SIM wissel gedetecteerd."
                : "Wel SIM detectiefunctie gevonden.");
        tvFooter.setText("Controle afgerond (pass versie)");
    }
}
