package co.edu.udea.computacionmovil.parkingeasy;

/**
 * Created by estudiantelis on 31/10/16.
 */
public class Parqueadero {

    private String codigo;
    private String nombre;
    private String horario;
    private float latitud;
    private float longitud;
    private long valorhoramoto;
    private long valordiamoto;
    private long valorhoracarro;
    private long valordiacarro;
    private String descripcion;

    public Parqueadero(){

    }

    public Parqueadero(float latitud, float longitud, long valordiamoto) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.valordiamoto = valordiamoto;
    }

    public Parqueadero(String cod, String nombre, long valordiacarro, long valorhoracarro, long valordiamoto, String horario, float latitud, float longitud, long valorhoramoto, String descripcion) {
        this.codigo = cod;
        this.nombre = nombre;
        this.valordiacarro = valordiacarro;
        this.valorhoracarro = valorhoracarro;
        this.valordiamoto = valordiamoto;
        this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.valorhoramoto = valorhoramoto;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public long getValorhoramoto() {
        return valorhoramoto;
    }

    public void setValorhoramoto(long valorhoramoto) {
        this.valorhoramoto = valorhoramoto;
    }

    public long getValordiamoto() {
        return valordiamoto;
    }

    public void setValordiamoto(long valordiamoto) {
        this.valordiamoto = valordiamoto;
    }

    public long getValorhoracarro() {
        return valorhoracarro;
    }

    public void setValorhoracarro(long valorhoracarro) {
        this.valorhoracarro = valorhoracarro;
    }

    public long getValordiacarro() {
        return valordiacarro;
    }

    public void setValordiacarro(long valordiacarro) {
        this.valordiacarro = valordiacarro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
