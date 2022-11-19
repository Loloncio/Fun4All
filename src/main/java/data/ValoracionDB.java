package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Valoracion;

/**
 *
 * @author lolo
 */
public class ValoracionDB {
    public static int insert(Valoracion valoracion) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO Valoracion (Nombre, Sala, Opinion, Valoracion) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, valoracion.getUserName());
            ps.setString(2, valoracion.getSalaName());
            ps.setString(3, valoracion.getOpinion());
            ps.setFloat(4, valoracion.getPuntuacion());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static ArrayList<Valoracion> listarValoraciones(String nombre){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<Valoracion> valoraciones = new ArrayList<>();
        ResultSet rs = null;
        String query = "SELECT * FROM Valoracion WHERE nombre=?"; 
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while(rs.next()){
                Valoracion valoracion = new Valoracion();
                valoracion.setUserName(rs.getString("nombre"));
                valoracion.setSalaName(rs.getString("sala"));
                valoracion.setOpinion(rs.getString("opinion"));
                valoracion.setPuntuacion(rs.getFloat("valoracion"));
                valoraciones.add(valoracion);
            }
            return valoraciones;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public static ArrayList<Valoracion> listarValoracionesSala(String id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<Valoracion> valoraciones = new ArrayList<>();
        ResultSet rs = null;
        String query = "SELECT * FROM Valoracion WHERE Sala=?"; 
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                Valoracion valoracion = new Valoracion(rs.getString("nombre"), 
                        rs.getString("sala"),rs.getString("opinion"),rs.getFloat("valoracion"));
                valoraciones.add(valoracion);
            }
            return valoraciones;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public static boolean existsVal(String id, String nombre){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Valoracion WHERE nombre = ? and sala=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, id);
            rs = ps.executeQuery();
            boolean res = rs.next();
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
