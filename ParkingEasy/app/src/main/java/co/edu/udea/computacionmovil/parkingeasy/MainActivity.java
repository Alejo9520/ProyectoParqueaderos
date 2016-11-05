package co.edu.udea.computacionmovil.parkingeasy;

import android.Manifest;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {


    //nuevo comentario

    private MapFragment mMapFragment; //Para llamar al Fragment
    private GoogleApiClient mGoogleApiClient = null;
    private Marker userLocation; //Marcador que apunta a la ubicacion del usuario
    private ArrayList<Parqueadero> parqueaderoArrayList; //Arreglo local de parqueaderos
    private Location location; //Variable que guarda todos los datos relacionados con la ubicacion del usuario
    private Parqueadero inicialesParqueadero[] = new Parqueadero[2]; //Parqueaderos registrados inicialmente

    private static String PARQUEADEROS = "parqueaderos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parqueaderoArrayList = new ArrayList();

        Parqueadero p = new Parqueadero("m0","PARQUEADERO LA 80",200,100,250,"MJ8-10",6.272603F,-75.572250F,120, "descripcion1");
        inicialesParqueadero[0] = p;
        p = new Parqueadero("m1","PARQUEADERO CERVEZA",200,100,250,"WV6-10",6.260288F,-75.571000F,120, "descripcion2ñlkjhgflkjhgfluyikdysju67ydjf6uyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyrdhhhhhhhhhhhhhhhd6u75zu54hyctytytytytytytytytytyty");
        inicialesParqueadero[1] = p;




        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        for (int i = 0; i < inicialesParqueadero.length; i++) {
            database.child(PARQUEADEROS).child(String.valueOf(i)).setValue(inicialesParqueadero[i]);
        }

        mMapFragment = MapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map_container, mMapFragment)
                .commit();

        // Registrar escucha onMapReadyCallback
        mMapFragment.getMapAsync(this);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 50, this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.d("ggg", "valor de location: " + location);


        //Intent para la conexion con los otros fragments
        Intent i = this.getIntent();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        //Camara ubicada en Medellin
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(false);

        LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(miUbicacion)
                .zoom(15)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



        //Base de datos de parqueaderos

        final Float parqueaderos[][] = new Float[8][2];
        //Parqueadero AUTO STAR
        parqueaderos[0][0] = 6.266111F;
        parqueaderos[0][1] = -75.563736F;
        //Parqueadero edificio turin
        parqueaderos[1][0] = 6.260288F;
        parqueaderos[1][1] = -75.571000F;
        //Parqueadero los medicos leon XXIII
        parqueaderos[2][0] = 6.266988F;
        parqueaderos[2][1] = -75.564358F;
        //Parqueadero el cafetero
        parqueaderos[3][0] = 6.273451F;
        parqueaderos[3][1] = -75.561311F;
        //Parqueadero publico Caribe
        parqueaderos[4][0] = 6.271382F;
        parqueaderos[4][1] = -75.573778F;
        //Parqueadero Colanta
        parqueaderos[5][0] = 6.272603F;
        parqueaderos[5][1] = -75.572250F;
        //Parqueadero Lubricantes Medellin
        parqueaderos[6][0] = 6.272543F;
        parqueaderos[6][1] = -75.572986F;
        //Parqueadero Placita Zea
        parqueaderos[7][0] = 6.253827F;
        parqueaderos[7][1] = -75.571624F;



        //Parqueadero p = new Parqueadero("nombre1",200,100,250,"MJ8-10",6.266111F,-75.563736F,120);
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.child(PARQUEADEROS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Parqueadero parq = dataSnapshot.getValue(Parqueadero.class);

                LatLng parqueadero = new LatLng(parq.getLatitud(), parq.getLongitud());

                Marker m = googleMap.addMarker(new MarkerOptions()
                        .position(parqueadero)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
                        .title(parq.getNombre()));

                parq.setCodigo(m.getId());
                if (!parqueaderoArrayList.contains(parq)){
                    parqueaderoArrayList.add(parq);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Parqueadero parq = dataSnapshot.getValue(Parqueadero.class);

                LatLng parqueadero = new LatLng(parq.getLatitud(), parq.getLongitud());
                MarkerOptions mO = new MarkerOptions()
                        .position(parqueadero)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
                        .title(parq.getNombre());
                googleMap.addMarker(mO);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //onChildAdded(dataSnapshot, "elimindao");
                MainActivity.this.finish();
                startActivity(new Intent(MainActivity.this, MainActivity.class));

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /**
         * Metodo que es llamado al momento de presionar un parqueadero
         */
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d("marker", ""+parqueaderoArrayList.size());
                for (int i=0; i<parqueaderoArrayList.size();i++){
                    Parqueadero p = parqueaderoArrayList.get(i);
                    if (p.getCodigo().equals(marker.getId())){
                        Log.d("marker",marker.getId());
                        Toast.makeText(MainActivity.this, "Se presiono un marcador", Toast.LENGTH_SHORT).show();

                        /**
                         * Metodo que le manda los datos al fragment de Parqueadero
                         */

                        Fragment parqueaderoF = new ParqueaderoFragment();
                        ((ParqueaderoFragment)parqueaderoF).setDatos(p.getNombre(), p.getValordiacarro(), p.getValorhoracarro(), p.getValordiamoto(), p.getHorario(), p.getLatitud(), p.getLongitud(), p.getValorhoramoto(), p.getDescripcion());
                        setTitle(p.getNombre());
                        final FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.addToBackStack("algo");
                        //final FragmentManager fragmentManager = getFragmentManager();

                        ft.replace(R.id.map_container, parqueaderoF).commit();


                    }
                }

                return true;
            }
        });

        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener(){
            @Override
            public void onMapLongClick(LatLng latLng) {
                Parqueadero p = new Parqueadero("" + parqueaderoArrayList.size(), "nombre" + parqueaderoArrayList.size(), 200, 100, 250, "MJ8-10", (float) latLng.latitude, (float) latLng.longitude, 120, "descripcion");
                database.child(PARQUEADEROS).child(String.valueOf(parqueaderoArrayList.size())).setValue(p);
            }
        });


        /*for (int i = 0; i < 8; i++) {
            int j = 0;
            LatLng parqueadero = new LatLng(parqueaderos[i][j], parqueaderos[i][j + 1]);

            googleMap.addMarker(new MarkerOptions()
                    .position(parqueadero)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
                    .title("PARQUEADERO"));

        }*/

        if(location!=null) {
            userLocation = googleMap.addMarker(new MarkerOptions().title("Mi ubicacion").icon(BitmapDescriptorFactory.fromResource(R.drawable.moto)).position(new LatLng(location.getLatitude(), location.getLongitude())));
        }else{
            Toast.makeText(MainActivity.this, "no hay localizaciòn", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(MainActivity.this, "Hola "+userLocation.getPosition(), Toast.LENGTH_SHORT).show();
        if(userLocation!=null){
            userLocation.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("MainActivity","Habilitado");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }
}














