package com.example.mauricio_nunes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    TextView imcResult, message, pesoResult, alturaResult, imcStatus;
    Double a = 0.0, m = 0.0 , imc = 0.0;
    String imcFormatado, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imcResult = findViewById(R.id.imcResult);
        message = findViewById(R.id.message);
        pesoResult = findViewById(R.id.pesoResult);
        alturaResult = findViewById(R.id.alturaResult);
        imcStatus = findViewById(R.id.imcStatus);

        if (savedInstanceState != null) {
            a = savedInstanceState.getDouble("altura");
            alturaResult.setText("Altura: " + a + " m");

            m = savedInstanceState.getDouble("peso");
            pesoResult.setText("Peso: " + m + " kg");

            status = savedInstanceState.getString("status");
            imcStatus.setText(status);

            imcFormatado = savedInstanceState.getString("imc");
            imcResult.setText("IMC: " + imcFormatado);

            message.setText("");
        }
    }

    public void execute(View view) {
        Intent intent = new Intent(MainActivity.this, imc_form.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                a = data.getDoubleExtra(imc_form.ALTURA, 0);
                m = data.getDoubleExtra(imc_form.PESO, 0);

                imc = m/(a*a);

                DecimalFormat decimal = new DecimalFormat("0.00");
                imcFormatado = decimal.format(imc);


                message.setText("");
                pesoResult.setText("Peso: " + m + " kg");
                alturaResult.setText("Altura: " + a + " m");
                imcResult.setText("IMC: " + imcFormatado);

                if (imc < 17) {
                    status = "Situação: Muito abaixo do peso";
                } else if (imc >= 17 && imc <= 18.49) {
                    status = "Situação: Abaixo do peso";
                } else if (imc >= 18.5 && imc <= 24.99) {
                    status = "Situação: Peso normal";
                } else if (imc >= 25 && imc <= 29.99) {
                    status = "Situação: Acima do Peso";
                } else if (imc >= 30 && imc <= 34.99) {
                    status = "Situação: Obesidade I";
                } else if (imc >= 35 && imc <= 39.99) {
                    status = "Situação: Obesidade II (severa)";
                } else {
                    status = "Situação: Ovesidade III (mórbida)";
                }
                imcStatus.setText(status);

            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("peso", m);
        outState.putDouble("altura", a);
        outState.putString("imc", imcFormatado);
        outState.putString("status", status);
    }
}