package com.crraul.pedircita;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CitaReservada extends AppCompatActivity {

    TextView date;
    TextView time;
    TextView dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cita_reservada);

        Intent intent = getIntent();

        String dateValue = intent.getIntExtra("day", 1)+"/"+intent.getIntExtra("month", 1)+"/"+intent.getIntExtra("year", 1900);
        String timeValue = intent.getIntExtra("hour", 0)+" : "+intent.getIntExtra("minute", 0);
        String dniValue = intent.getStringExtra("dni");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        dni = findViewById(R.id.dni);

        date.setText("Día: "+dateValue);
        time.setText("Hora: "+timeValue);
        dni.setText("DNI: "+dniValue);
    }
}