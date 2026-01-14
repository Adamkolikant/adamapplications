package com.katza.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class TointentActivity extends AppCompatActivity {

    EditText etBirthYear;
    Button btnSaveAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tointent);

        etBirthYear = findViewById(R.id.etBirthYear);
        btnSaveAge = findViewById(R.id.btnSaveAge);

        btnSaveAge.setOnClickListener(v -> {


            String yearText = etBirthYear.getText().toString();

            if (!yearText.isEmpty()) {
                int birthYear = Integer.parseInt(yearText);
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int age = currentYear - birthYear;

                Intent intent = new Intent();
                intent.putExtra("age", age);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
