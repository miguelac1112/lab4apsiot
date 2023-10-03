package com.example.lab4iot;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AcelerometroFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float lastAcceleration = 0.0f;
    private static final float SHAKE_THRESHOLD = 15.0f;

    private List<Persona> personaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ResultsAdapter resultsAdapter;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView20);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        if (personaList == null) {
            personaList = new ArrayList<>(); // Inicializa la lista si es nula
        }

        resultsAdapter = new ResultsAdapter(getContext(), personaList);
        recyclerView.setAdapter(resultsAdapter);

        SensorManager sensorManager = (SensorManager) requireActivity().getSystemService(SENSOR_SERVICE);
        if (sensorManager != null){
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (sensor!=null){
                Log.d("msg-test", "si tengo acelerometro");
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
            }else{
                Log.d("msg-test","no tengo acelerometro");
            }
        }

    }

    public void addContact(Persona persona) {
        if (personaList != null && resultsAdapter != null) {
            personaList.add(persona);
            resultsAdapter.notifyDataSetChanged();
        }
    }

    public void updateContactList(List<Persona> personas) {
        this.personaList.clear();
        this.personaList.addAll(personas);
        resultsAdapter.notifyDataSetChanged();
    }

    public void setContactList(List<Persona> personas) {
        if (this.personaList != null) {
            this.personaList.clear();
            this.personaList.addAll(personas);
            if (resultsAdapter != null) {
                resultsAdapter.notifyDataSetChanged();
            }
        }
    }

    public void removeContact(int position) {
        personaList.remove(position);
        resultsAdapter.notifyItemRemoved(position);
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

            int itemCount = recyclerView.getAdapter().getItemCount();
            if (itemCount>0)
                recyclerView.smoothScrollToPosition(itemCount-1);
        }

        lastAcceleration = acceleration;
    }

    private float calculateAcceleration(float values[]) {
        return (float) Math.sqrt(values[0] * values[0] + values[1] * values[1] + values[2] * values[2]);
    }
}