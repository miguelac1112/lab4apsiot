package com.example.lab4iot;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


public class MagnetometroFragment extends Fragment implements SensorEventListener {

    private List<Persona> personaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ResultsAdapter resultsAdapter;
    private SensorManager sensorManager;
    private Sensor magneticFieldSensor;
    private float[] mGeomagnetic = new float[3];

    public MagnetometroFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_magnetometro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView10);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        if (personaList == null) {
            personaList = new ArrayList<>(); // Inicializa la lista si es nula
        }

        resultsAdapter = new ResultsAdapter(getContext(), personaList);
        recyclerView.setAdapter(resultsAdapter);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }

    public void addContact(Persona persona) {
        if (personaList != null && resultsAdapter != null) {
            personaList.add(persona);
            resultsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, magneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mGeomagnetic = event.values;
            float azimuth = (float) Math.toDegrees(mGeomagnetic[0]);
            azimuth = (azimuth + 360) % 360;
            float startAngle = 0.0f;
            float endAngle = 180.0f;
            float opacity = 1.0f;
            if (azimuth >= startAngle && azimuth <= endAngle) {
                float range = endAngle - startAngle;
                float adjustedAngle = azimuth - startAngle;
                opacity = 1.0f - (adjustedAngle / range);
            }
            recyclerView.setAlpha(opacity);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


}
