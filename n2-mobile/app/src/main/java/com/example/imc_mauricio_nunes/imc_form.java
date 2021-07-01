package com.example.mauricio_nunes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class imc_form extends AppCompatActivity {

    EditText editPeso, editAltura;
    public static final String ALTURA = "altura";
    public static final String PESO = "peso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc_form);

        editPeso = findViewById(R.id.editPeso);
        editAltura = findViewById(R.id.editAltura);

    }

    public void calculate(View view){
        double m = Double.parseDouble(editPeso.getText().toString());
        double a = Double.parseDouble(editAltura.getText().toString());

        Intent intent = new Intent();

        intent.putExtra(ALTURA, a);
        intent.putExtra(PESO, m);

        setResult(RESULT_OK, intent);
        finish();
    }

}