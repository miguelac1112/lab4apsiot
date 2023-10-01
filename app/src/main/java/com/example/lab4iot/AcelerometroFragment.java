package com.example.lab4iot;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class AcelerometroFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float lastAcceleration = 0.0f;
    private static final float SHAKE_THRESHOLD = 15.0f;

    public AcelerometroFragment() {
        // Constructor vacío requerido.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acelerometro, container, false);

        // Inicializar el SensorManager y el acelerómetro
        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Registrar el listener del acelerómetro
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Detener la escucha del acelerómetro para liberar recursos
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No necesitas hacer nada aquí
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Manejar eventos de agitación
        float acceleration = calculateAcceleration(event.values);

        if (acceleration > SHAKE_THRESHOLD && lastAcceleration <= SHAKE_THRESHOLD) {
            // Agitación detectada, muestra un Toast con la aceleración
            String message = "Su aceleración: " + acceleration + " m/s^2";
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
        }

        lastAcceleration = acceleration;
    }

    private float calculateAcceleration(float values[]) {
        return (float) Math.sqrt(values[0] * values[0] + values[1] * values[1] + values[2] * values[2]);
    }
}