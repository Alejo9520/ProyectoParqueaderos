package co.edu.udea.computacionmovil.parkingeasy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrarParqueadero extends Fragment {

    private Parqueadero parqueadero;
    private FragmentManager fragmentManager;
    Button btnregistrar;
    EditText txtNombre;
    EditText txtHorario;
    EditText txtValorHoraMoto;
    EditText txtValorHoraCarro;
    EditText txtValorDiaMoto;
    EditText txtValorDiaCarro;
    EditText txtLatitud;
    EditText txtLongitud;
    EditText txtDescripcion;

    String nombre;
    String horario;
    long valorhoramoto;
    long valorhoracarro;
    long valordiamoto;
    long valordiacarro;
    String descripcion;

    public RegistrarParqueadero() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registrar_parqueadero, container, false);
        btnregistrar = (Button) v.findViewById(R.id.btnRegistrar);
        txtNombre = (EditText) v.findViewById(R.id.editNombrepar);
        txtHorario = (EditText) v.findViewById(R.id.editHorario);
        txtValorHoraMoto = (EditText) v.findViewById(R.id.editHoraM);
        txtValorHoraCarro = (EditText) v.findViewById(R.id.editHoraC);
        txtValorDiaMoto = (EditText) v.findViewById(R.id.editValorDiaM);
        txtValorDiaCarro = (EditText) v.findViewById(R.id.editValorDiaC);
        txtDescripcion = (EditText) v.findViewById(R.id.editDescripcion);



        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                int cantidad = (int) parqueadero.getValordiamoto();
                nombre = txtNombre.getText().toString();
                horario = txtHorario.getText().toString();
                descripcion = txtDescripcion.getText().toString();

                if(nombre.equals("") || horario.equals("") || txtValorHoraMoto.getText().toString().equals("") || txtValorHoraCarro.getText().toString().equals("") ||
                        txtValorDiaMoto.getText().toString().equals("") || txtValorDiaCarro.getText().toString().equals("")){
                    Toast.makeText(v.getContext(),"Falta completar campos obligatorios",Toast.LENGTH_SHORT).show();
                }else {

                    valorhoramoto = Long.parseLong(txtValorHoraMoto.getText().toString());
                    valorhoracarro = Long.parseLong(txtValorHoraCarro.getText().toString());
                    valordiamoto = Long.parseLong(txtValorDiaMoto.getText().toString());
                    valordiacarro = Long.parseLong(txtValorDiaCarro.getText().toString());


                    parqueadero.setNombre(nombre);
                    parqueadero.setHorario(horario);
                    parqueadero.setValorhoramoto(valorhoramoto);
                    parqueadero.setValorhoracarro(valorhoracarro);
                    parqueadero.setValordiamoto(valordiamoto);
                    parqueadero.setValordiacarro(valordiacarro);
                    parqueadero.setDescripcion(descripcion);


                    database.child(MainActivity.PARQUEADEROS).child(String.valueOf(cantidad)).setValue(parqueadero);

                    Toast.makeText(v.getContext(), "Parqueadero Registrado Correctamente", Toast.LENGTH_SHORT).show();
                    fragmentManager.popBackStack();

                }

            }
        });

        return v;
    }

    public void setDatos(Parqueadero p, FragmentManager fm){
        fragmentManager = fm;
        parqueadero = p;
    }

}
