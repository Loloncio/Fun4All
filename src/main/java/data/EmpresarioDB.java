package data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import javax.servlet.http.Part;
import modelo.Empresario;

public class EmpresarioDB {

    public static int insert(Empresario empresario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO EMPRESARIO (Nombre, nombreEmp, email, password, NIF, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, empresario.getNombre());
            ps.setString(2, empresario.getNombreEmp());
            ps.setString(3, empresario.getEmail());
            ps.setString(4, empresario.getPassword());
            ps.setString(5, empresario.getNIF());
            ps.setString(6, empresario.getDireccion());
            ps.setInt(7, empresario.getTelefono());
            //ps.setBlob(8, user.getFoto().getInputStream());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT EMAIL FROM Empresario "
                + "WHERE EMAIL = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
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

    public static Empresario selectUser(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Empresario WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            Empresario empresario = null;
            if (rs.next()) {
                empresario = new Empresario();
                empresario.setNombre(rs.getString("Nombre"));
                empresario.setEmail(rs.getString("Email"));
                empresario.setTelefono(rs.getInt("telefono"));
                empresario.setDireccion(rs.getString("direccion"));
                empresario.setNIF(rs.getString("NIF"));
                empresario.setNombreEmp(rs.getString("nombreEmp"));
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return empresario;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean passCorrecta(String password, String email) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement statement = null;
            statement = connection.prepareStatement(
                    "SELECT nombre FROM Empresario WHERE email=? and password=? ");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            boolean res = result.next();
            pool.freeConnection(connection);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void getImagen(String email, OutputStream respuesta) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement statement = null;
            statement = connection.prepareStatement(
                    "SELECT Imagen FROM Empresario WHERE email=?");
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Blob blob = result.getBlob("Imagen");
                if (!result.wasNull() && blob.length() > 1) {
                    InputStream imagen = blob.getBinaryStream();
                    byte[] buffer = new byte[1000];
                    int len = imagen.read(buffer);
                    while (len != -1) {
                        respuesta.write(buffer, 0, len);
                        len = imagen.read(buffer);
                    }
                    imagen.close();
                }
            }
            pool.freeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int setImagen(String nombre, Part imagen) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE Usuario SET Imagen=? WHERE nombre = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setBlob(1, imagen.getInputStream());
            ps.setString(2, nombre);
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static boolean hayImagen(String email) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement statement = null;
            statement = connection.prepareStatement(
                    "SELECT Imagen FROM Empresario WHERE email=?");
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Blob blob = result.getBlob("Imagen");
                if (blob == null || result.wasNull()){
                    pool.freeConnection(connection);
                    return false;
                }
                    pool.freeConnection(connection);
                    return true;
                }            
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
