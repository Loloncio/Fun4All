package data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import javax.servlet.http.Part;
import modelo.Usuario;

public class UserDB {

    public static int insert(Usuario user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO USUARIO (Nombre, Email, Password, FechaNacimiento) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDate(4, user.getNacimiento());
            //ps.setBlob(5, user.getFoto().getInputStream());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean userExists(String usuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT nombre FROM Usuario WHERE nombre = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usuario);
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

    public static Usuario selectUser(String usuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Usuario WHERE nombre = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            Usuario user = null;
            if (rs.next()) {
                user = new Usuario();
                user.setNombre(rs.getString("Nombre"));
                user.setEmail(rs.getString("Email"));
                user.setNacimiento(rs.getDate("FechaNacimiento"));
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void getImagen(String nombre, OutputStream respuesta) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement statement = null;
            statement = connection.prepareStatement(
                    "SELECT Imagen FROM Usuario WHERE nombre=?");
            statement.setString(1, nombre);
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

    public static boolean passCorrecta(String password, String usuario) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement statement = null;
            statement = connection.prepareStatement(
                    "SELECT nombre FROM Usuario WHERE nombre=? and password=? ");
            statement.setString(1, usuario);
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
    public static boolean hayImagen(String nombre) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement statement = null;
            statement = connection.prepareStatement(
                    "SELECT Imagen FROM Usuario WHERE nombre=?");
            statement.setString(1, nombre);
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
