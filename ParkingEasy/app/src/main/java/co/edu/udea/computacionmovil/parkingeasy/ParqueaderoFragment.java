package co.edu.udea.computacionmovil.parkingeasy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;


/**
 * Fragment con toda la informacion del parqueadero seleccionado
 */
public class ParqueaderoFragment extends Fragment {

    TextView txtNombre;
    TextView txtHorario;
    TextView txtValorHoraMoto;
    TextView txtValorHoraCarro;
    TextView txtValorDiaMoto;
    TextView txtValorDiaCarro;
    TextView txtLatitud;
    TextView txtLongitud;
    TextView txtDescripcion;
    String nombre;
    String horario;
    long valorhoramoto;
    long valorhoracarro;
    long valordiamoto;
    long valordiacarro;
    float latitud;
    float longitud;
    String descripcion;


    public ParqueaderoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parqueadero, container, false);

        txtNombre = (TextView) view.findViewById(R.id.tvNombreParq);
        txtHorario = (TextView) view.findViewById(R.id.tvHorarioinfo);
        txtValorDiaCarro = (TextView)view.findViewById(R.id.tvDiaCarro);
        txtValorDiaMoto = (TextView)view.findViewById(R.id.tvDiaMoto);
        txtValorHoraCarro = (TextView) view.findViewById(R.id.tvHoraCarro);
        txtValorHoraMoto = (TextView) view.findViewById(R.id.tvHoraMoto);
        txtLatitud = (TextView) view.findViewById(R.id.tvLatitud);
        txtLongitud = (TextView) view.findViewById(R.id.tvLongitud);
        txtDescripcion = (TextView) view.findViewById(R.id.tvDescripcion);

        //Actualizacion de datos

        txtNombre.setText(nombre+"");
        txtHorario.setText(horario+"");
        txtValorDiaCarro.setText("Carro: "+valordiacarro+"");
        txtValorDiaMoto.setText("Moto: "+valordiamoto+"");
        txtValorHoraCarro.setText("Carro: "+valorhoracarro + "");
        txtValorHoraMoto.setText("Moto: "+valorhoramoto+"");
        txtLatitud.setText(latitud+"");
        txtLongitud.setText(longitud+"");
        txtDescripcion.setText(descripcion+"");

        return view;

    }

    public void setDatos(String nombr, long valordiacarr, long valorhoracarr, long valordiamot, String horari, float latitu, float longitu, long valorhoramot, String descripcio) {

        nombre = nombr;
        valordiacarro = valordiacarr;
        valorhoracarro = valorhoracarr;
        valordiamoto = valordiamot;
        valorhoramoto = valorhoramot;
        horario = horari;
        latitud = latitu;
        longitud = longitu;
        descripcion = descripcio;
    }

}
