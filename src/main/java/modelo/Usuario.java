package modelo;

import java.sql.Date;
import java.io.Serializable;
import javax.servlet.http.Part;

public class Usuario implements Serializable {

    private String nombre;
    private String password;
    private String email;
    private Date nacimiento;
    private Part foto;

    public Usuario() {
        nombre = "";
        password = "";
        email = "";
        nacimiento = null;
        foto = null;
    }

    public Usuario(String nombre, String password, String email, Date nacimiento) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.nacimiento = nacimiento;    
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    /*public Part getFoto() {
        return foto;
    }*/

}
