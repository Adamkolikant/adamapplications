package com.katza.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    EditText etName, etGender;
    TextView tvAge;
    Button btnEditAge, btnSubmit;

    int selectedAge = 0;

    // ====== הקוד מהתמונה (מותאם) ======
    ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                selectedAge = data.getIntExtra("age", 0);
                                tvAge.setText("גיל: " + selectedAge);
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.etName);
        etGender = findViewById(R.id.etGender);
        tvAge = findViewById(R.id.tvAge);
        btnEditAge = findViewById(R.id.btnEditAge);
        btnSubmit = findViewById(R.id.btnSubmit);

        // לחיצה על "ערוך גיל"
        btnEditAge.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, TointentActivity.class);
            launcher.launch(intent);
        });

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String gender = etGender.getText().toString();

            Intent intent = new Intent(MainActivity3.this, TointentActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("age", selectedAge);
            intent.putExtra("gender", gender);

            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.activity_main1) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        if (id == R.id.activity_main2) {
            startActivity(new Intent(this, MainActivity2.class));
            finish();
        }

        if (id == R.id.activity_main3) {
            startActivity(new Intent(this, MainActivity3.class));
            finish();
        }

        return true;
    }
}
