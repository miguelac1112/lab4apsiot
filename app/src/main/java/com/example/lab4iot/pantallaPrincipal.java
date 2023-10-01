package com.example.lab4iot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

public class pantallaPrincipal extends AppCompatActivity {

    private boolean isAcelerometroFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        Button buttonCambiarSensor = findViewById(R.id.button2);

        buttonCambiarSensor.setOnClickListener(v -> {
            if (isAcelerometroFragmentDisplayed) {
                loadMagnetometroFragment();
            } else {
                loadAcelerometroFragment();
            }
            isAcelerometroFragmentDisplayed = !isAcelerometroFragmentDisplayed;
        });
    }

    private void loadMagnetometroFragment() {
        Fragment magnetometroFragment = new MagnetometroFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView3, magnetometroFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadAcelerometroFragment() {
        Fragment acelerometroFragment = new AcelerometroFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView3, acelerometroFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}