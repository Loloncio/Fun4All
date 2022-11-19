package modelo;

import java.sql.Date;
import java.io.Serializable;
import java.sql.Time;
import javax.servlet.http.Part;

public class Sala implements Serializable {
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private float precio;
    private Date horario;
    private boolean infantil;
    private int capacidad;
    private String categoria;
    private float valoracion;
    private Part imagen;
    private String id;
    private Time hora;
    
    public Sala(){
        nombre = "";
        descripcion = "";
        ubicacion = "";
        precio = 0;
        horario = null;
        infantil = false; 
        capacidad = 0;
        categoria = "";
        valoracion = 0;
        imagen = null;
        id = "";
        hora=null;
    }
    public Sala(String nom, String desc, String ubic,
            float prec, Date hora, boolean inf, int cap, String cat, Part foto){
        nombre = nom;
        descripcion = desc;
        ubicacion = ubic;
        precio = prec;
        horario = hora;
        infantil = inf; 
        capacidad = cap;
        categoria = cat;
        valoracion = 0;
        imagen = foto;
        hora=null;
    }
    public void setNombre(String nom){
        nombre = nom;
    }
    public void setDescripcion(String desc){
        descripcion = desc;
    }
    public void setHorario(Date hora){
        horario = hora;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setCapacidad(int cap){
        capacidad = cap;
    }
    public void setCategoria(String cat){
        categoria = cat;
    }
    public void agregaCategoria(String cat){
        categoria += cat;
    }
    public void setPrecio(float prec){
        precio = prec;
    }
    public void setUbicacion(String ubic){
        ubicacion = ubic;
    }
    public void setValoracion(float val){
        valoracion = val;
    }
    public void setImagen(Part foto){
        imagen = foto;
    }
    public void setInfantil(boolean inf){
        infantil=inf;
    }
    public void setHorario(Time hora){
        this.hora = hora;
    }
    public String getNombre(){
        return nombre;
    }
    public String getDescripcion(){
       return descripcion; 
    }
    public String getUbicacion(){
        return ubicacion;
    }
    public float getPrecio(){
        return precio;
    }
    public Date getHorario(){
        return horario;
    }
    public boolean getInfantil(){
        return infantil;
    }
    public float getValoracion(){
        return valoracion;
    }
    public Part getImagen(){
        return imagen;
    }
    public int getCapacidad(){
        return capacidad;
    }
    public String getId(){
        return id;
    }
    public Time getHora(){
        return hora;
    }
}
