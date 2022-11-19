/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author ruixin
 */
public class Reserva {
    private String userName;
    private int salaId;
    private Date dia;
    private Time hora;
    private float precio;
    
    public Reserva() {
        userName = "";
        salaId = 0;
        dia = null;
        hora = null;
        precio = 0;
    }
    
    public Reserva(String userName, int salaId, Date dia, Time hora, float precio) {
        this.userName= userName;
        this.salaId = salaId;
        this.dia = dia;
        this.hora= hora;
        this.precio = precio;
    }
    
    public void setNombreUser(String userName) {
        this.userName = userName;
    }

    public void setSalaId(int salaId) {
        this.salaId = salaId;
    }
    
    public void setDia(Date dia) {
        this.dia = dia;
    }
    
    public void setHora(Time hora) {
       this.hora = hora;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }
   
    public String getNombreUser() {
        return userName;
    }

    public int getSalaId() {
        return salaId;
    }
    
    public Date getDia() {
        return dia;
    }
    
    public Time getHora() {
        return hora;
    }
    
    public float getPrecio() {
        return precio;
    }
}
