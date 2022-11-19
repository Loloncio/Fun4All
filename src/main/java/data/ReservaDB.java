package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Reserva;

/**
 *
 * @author lolo
 */
public class ReservaDB {
    public static int insert(Reserva reservar) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        String query="INSERT INTO RESERVA(Nombre, Sala, Fecha, Hora, Precio) VALUES (?, ?, ?,?)";
        try {
            ps= connection.prepareStatement(query);
            ps.setString(1, reservar.getNombreUser());
            ps.setInt(2, reservar.getSalaId());
            ps.setDate(3, reservar.getDia());
            ps.setTime(4, reservar.getHora());
            ps.setFloat(5, reservar.getPrecio());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static ArrayList<Reserva> listarReservas(String nombre){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<Reserva> valoraciones = new ArrayList<>();
        ResultSet rs = null;
        String query = "SELECT * FROM Reserva WHERE nombre=?"; 
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while(rs.next()){
                Reserva reserva = new Reserva();
                reserva.setNombreUser(rs.getString("nombre"));
                reserva.setSalaId(rs.getInt("sala"));
                reserva.setDia(rs.getDate("fecha"));
                reserva.setHora(rs.getTime("hora"));
                reserva.setPrecio(rs.getFloat("precio"));
                valoraciones.add(reserva);
            }
            return valoraciones;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
