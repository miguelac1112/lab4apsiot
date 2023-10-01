package com.example.lab4iot;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.LeadingMarginSpan;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ImageView;

public class pantallaPrincipal extends AppCompatActivity {

    private boolean isAcelerometroFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(z -> showSensorDescriptionDialogMagnetometro());

        Button buttonCambiarSensor = findViewById(R.id.button2);
        buttonCambiarSensor.setText("Ir a Acelerómetro");

        buttonCambiarSensor.setOnClickListener(v -> {
            if (isAcelerometroFragmentDisplayed) {
                imageView.setOnClickListener(z -> showSensorDescriptionDialogMagnetometro());
                loadMagnetometroFragment();
                buttonCambiarSensor.setText("Ir a Acelerómetro");
            } else {
                imageView.setOnClickListener(z -> showSensorDescriptionDialogAcelerometro());
                loadAcelerometroFragment();
                buttonCambiarSensor.setText("Ir a Magnetómetro");
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

    private void showSensorDescriptionDialogMagnetometro() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Detalles-Magnetómetro");

        SpannableString spannableString = new SpannableString("Haga CLICK en 'Añadir' para agregar contactos a su lista. Esta aplicación está utilizando el MAGNETÓMETRO de su dispositivo.\n\nDe esta forma, la lista se mostrará al 100% cuando se apunte al NORTE. Caso contrario se desvanecerá...");
        spannableString.setSpan(new LeadingMarginSpan.Standard(100), spannableString.length() - 75, spannableString.length() - 47, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new LeadingMarginSpan.Standard(100), spannableString.length() - 46, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        builder.setMessage(spannableString);
        builder.setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSensorDescriptionDialogAcelerometro() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Descripción del Acelerómetro");

        SpannableString spannableString = new SpannableString("Haga CLICK en 'Añadir' para agregar contactos a su lista. Esta aplicación está utilizando el ACELERÓMETRO de su dispositivo.\n\nDe esta forma, la lista se hará scroll hacia abajo, cuando agite su dispositivo.");
        spannableString.setSpan(new LeadingMarginSpan.Standard(100), spannableString.length() - 75, spannableString.length() - 47, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new LeadingMarginSpan.Standard(100), spannableString.length() - 46, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        builder.setMessage(spannableString);
        builder.setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}