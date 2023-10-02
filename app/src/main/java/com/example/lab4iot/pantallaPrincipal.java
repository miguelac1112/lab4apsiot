package com.example.lab4iot;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lab4iot.dto.Profile;
import com.example.lab4iot.dto.Results;
import com.example.lab4iot.services.RandomUser;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pantallaPrincipal extends AppCompatActivity {

    private FragmentManager fragmentManager;
    RandomUser randomUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        randomUser = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RandomUser.class);

        TextView textViewTipoDeSensor = findViewById(R.id.tipoSensor);
        Button btnSensor = findViewById(R.id.button2);
        fragmentManager = getSupportFragmentManager();
        Button btnAnadir = findViewById(R.id.button3);
        btnAnadir.setOnClickListener(v -> addRandomContactToActiveFragment());

        loadMagnetometroFragment();

        btnSensor.setOnClickListener(v -> {
            if (textViewTipoDeSensor.getText().equals("Magnetómetro")) {
                loadAcelerometroFragment();
            } else {
                loadMagnetometroFragment();
            }
        });

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(v -> showSensorDescriptionDialog());

    }

    private void loadMagnetometroFragment() {
        Fragment magnetometroFragment = new MagnetometroFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView3, magnetometroFragment);
        transaction.commit();
        transaction.addToBackStack(null);

        TextView textSensor = findViewById(R.id.tipoSensor);
        textSensor.setText("Magnetómetro");

        Button buttonCambiar = findViewById(R.id.button2);
        buttonCambiar.setText("Ir a Acelerómetro");
    }

    private void loadAcelerometroFragment() {
        Fragment acelerometroFragment = new AcelerometroFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView3, acelerometroFragment);
        transaction.commit();
        transaction.addToBackStack(null);

        TextView textSensor = findViewById(R.id.tipoSensor);
        textSensor.setText("Acelerómetro");

        Button buttonCambiar = findViewById(R.id.button2);
        buttonCambiar.setText("Ir a Magnetómetro");

    }

    private void showSensorDescriptionDialog() {
        Fragment activeFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView3);
        String message;
        String titulo;

        if (activeFragment instanceof AcelerometroFragment) {
            titulo ="Detalles - Acelerómetro";
            message = "Haga CLICK en Añadir para agregar contactos a su lista. Esta aplicación está utilizando el ACELEROMETRO de su dispositivo. De esta forma, la lista hará scroll hacia abajo, cuando agite su dispositivo.";
        } else if (activeFragment instanceof MagnetometroFragment) {
            titulo ="Detalles - MAGNETÓMETRO";
            message = "Haga CLICK on Añadir para agregar contactos a su lista. Esta aplicación está utilizando el MAGNETÓMETRO de su dispositivo. De esta forma, la lista se mostrara al 100% cuando se apurte al NORTE. Caso contrario se desvanecerá";
        } else {
            return;
        }

        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle(titulo)
                .setMessage(message)
                .setPositiveButton("Aceptar", null)
                .show();
    }

    private void addRandomContactToActiveFragment() {
        fetchProfileFromWs();
    }

    public void fetchProfileFromWs() {
        randomUser.getResults().enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful()) {
                    Profile profile = response.body();
                    if (profile != null) {
                        List<Results> results = profile.getResults();
                        if (results != null && !results.isEmpty()) {
                            String name = results.get(0).getName().getTitle()+" "+results.get(0).getName().getFirst()+" "+results.get(0).getName().getLast();
                            Persona persona = new Persona(results.get(0).getPicture().getThumbnail(),name,results.get(0).getGender(),results.get(0).getLocation().getCity(),results.get(0).getLocation().getCountry(),results.get(0).getEmail(),results.get(0).getPhone());

                            Fragment activeFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView3);

                            if (activeFragment instanceof AcelerometroFragment) {
                                /*por mientras no hacer nada*/
                            } else if (activeFragment instanceof MagnetometroFragment) {
                                MagnetometroFragment magnetometroFragment = (MagnetometroFragment) activeFragment;
                                magnetometroFragment.addContact(persona);
                            }
                        }
                    } else {
                        Log.d("msg-Error", "La lista de resultados está vacía o nula.");
                    }
                } else {
                    Log.d("msg-Error", "La respuesta no fue exitosa: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d("msg-llegue0", "Error al realizar la solicitud: " + t.getMessage());
            }
        });
    }
}