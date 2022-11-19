/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Ruixin
 */
public class Valoracion implements Serializable {
    private String nombreUsuario;
    private String sala;
    private String opinion;
    private float puntuacion;
    
    public Valoracion(){
        nombreUsuario = "";
        sala = "";
        opinion = "";
        puntuacion = 0;
    }
    
    public Valoracion(String userName, String salaName, String opinion, float puntuacion){
        this.nombreUsuario = userName;
        this.sala = salaName;
        this.opinion = opinion;
        this.puntuacion = puntuacion;
    }
    
    public void setUserName(String userName){
        this.nombreUsuario = userName;
    }
    
    public void setSalaName(String salaName){
        this.sala = salaName;
    }
    
    public void setOpinion(String opinion){
        this.opinion = opinion;
    }
    
    public void setPuntuacion(float puntuacion){
        this.puntuacion = puntuacion;
    }

    public String getUserName(){
        return nombreUsuario;
    }
    
    public String getSalaName(){
        return sala;
    }
    
    public String getOpinion(){
        return opinion;
    }
    
    public float getPuntuacion(){
        return puntuacion;
    }

    
}
