package com.example.lab4iot;
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


public class MagnetometroFragment extends Fragment {

    private List<Persona> personaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ResultsAdapter resultsAdapter;

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

}
