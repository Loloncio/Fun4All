package modelo;

import java.io.Serializable;

public class Empresario implements Serializable{
    private String nombre;
    private String NIF;
    private int telefono;
    private String email;
    private String direccion;
    private String password;
    private String nombreEmp;
    
    public Empresario(){
        nombre = "";
        NIF = "";
        telefono = 0;
        email = "";
        direccion = "";
        password = "";
        nombreEmp="";
    }
    public Empresario(String nom, String nomEmp, String email, String pass, String NIF,  String dir,  int tel){
        this.nombre = nom;
        this.nombreEmp = nomEmp;
        this.email = email;
        this.password = pass;
        this.NIF = NIF;
        this.direccion = dir;
        this.telefono = tel;
    }
    
    public void setNombre(String nom){
        this.nombre = nom;
    }
    public void setTelefono(int tel){
        this.telefono = tel;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setDireccion(String dir){
        this.direccion = dir;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public void setNIF(String NIF){
        this.NIF = NIF;
    }
    public void setNombreEmp(String nomEmp){
        this.nombreEmp = nomEmp;
    }
    public String getNombre(){
        return nombre;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getEmail(){
        return email;
    }
    public String getNIF(){
        return NIF;
    }
    public int getTelefono(){
        return telefono;
    }
    public String getPassword(){
        return password;
    }
    public String getNombreEmp(){
        return nombreEmp;
    }
    
}
