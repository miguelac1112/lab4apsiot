package com.example.lab4iot;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>{

    private List<Persona> personaList;
    private Context context;

    public ResultsAdapter(Context context, List<Persona> personaList) {
        this.context = context;
        this.personaList = personaList;
    }

    @NonNull
    @Override
    public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lista_personas, parent, false);
        return new ResultsViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull ResultsViewHolder holder, int position) {
        Persona persona = personaList.get(position);
        holder.bind(persona);
    }

    @Override
    public int getItemCount() {
        return personaList.size();
    }

    public class ResultsViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivPhoto;
        private TextView tvName, tvGender, tvCity, tvCountry, tvEmail, tvPhone;
        private ImageButton deleteButton;

       public ResultsViewHolder(@NonNull View itemView){

           super(itemView);
           ivPhoto = itemView.findViewById(R.id.imageView10);
           tvName = itemView.findViewById(R.id.textView9);
           tvGender = itemView.findViewById(R.id.textView15);
           tvCity = itemView.findViewById(R.id.textView16);
           tvCountry = itemView.findViewById(R.id.textView17);
           tvEmail = itemView.findViewById(R.id.textView18);
           tvPhone = itemView.findViewById(R.id.textView19);
       }

        public void bind(Persona persona) {
            // Configurar los elementos de la vista con los datos del contacto
            Glide.with(context)
                    .load(persona.getPhotoUrl())
                    .placeholder(R.drawable.google_icon)
                    .into(ivPhoto);
            tvName.setText(persona.getName());
            tvGender.setText(persona.getGender());
            tvCity.setText(persona.getCity());
            tvCountry.setText(persona.getCountry());
            tvEmail.setText(persona.getEmail());
            tvPhone.setText(persona.getPhone());


        }
   }

}
