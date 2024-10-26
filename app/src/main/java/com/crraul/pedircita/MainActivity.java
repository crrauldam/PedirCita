package com.crraul.pedircita;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    DatePicker calendar;
    TimePicker time;
    EditText dni;
    Button reserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // get layout elements
        calendar = findViewById(R.id.calendar);
        time = findViewById(R.id.time);
        time.setIs24HourView(true);
        dni = findViewById(R.id.dni);
        reserve = findViewById(R.id.reserve);

        reserve.setOnClickListener(view -> {
            int day = calendar.getDayOfMonth();
            int month = calendar.getMonth();
            int year = calendar.getYear();
            int hour = time.getHour();
            int minute = time.getMinute();

            if (isDateOk(day, month, year)) {
                if (isTimeOk(hour, minute)) {
                    if (isDNIOk(dni.getText().toString())) {
                        // crea un intent y le mete la info a pasar al otro activity
                        Intent intent = new Intent(MainActivity.this, CitaReservada.class);
                        intent.putExtra("day", day);
                        intent.putExtra("month", month);
                        intent.putExtra("year", year);
                        intent.putExtra("hour", hour);
                        intent.putExtra("minute", minute);
                        intent.putExtra("dni", dni.getText().toString());

                        // manda a la otra activity
                        startActivity(intent);
                    } else {
                        // muestra mensaje de dni no valido
                        Toast.makeText(this, "DNI no válido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // muestra mensaje de dia no valido (solo de L-V)
                    Toast.makeText(this, "Hora no válida", Toast.LENGTH_SHORT).show();
                }
            } else {
                // muestra mensaje de dia no valido (solo de L-V)
                Toast.makeText(this, "Día de la semana no válido", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean isDateOk(int year, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);

        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);

        return (dayOfWeek > 0 && dayOfWeek < 6);
    }


    private boolean isTimeOk(int hour, int minute) {
        boolean valid = false;

        if (hour >= 9 && hour <= 14) {
            if (hour == 14) {
                if (minute == 0) {
                    valid = true;
                }
            } else {
                valid = true;
            }
        }

        return valid;
    }


    private boolean isDNIOk(String dni) {
        return (dni != null && dni.matches("^\\d{8}[A-Za-z]$"));
    }
}