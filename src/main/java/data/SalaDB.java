package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import modelo.Sala;

public class SalaDB {

    public static int insert(Sala sala, String empresa) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO SALA (Nombre, Descripcion, "
                + "Ubicacion, Precio, Infantil, capacidad, imagen, empresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, sala.getNombre());
            ps.setString(2, sala.getDescripcion());
            ps.setString(3, sala.getUbicacion());
            ps.setFloat(4, sala.getPrecio());
            ps.setBoolean(5, sala.getInfantil());
            ps.setInt(6, sala.getCapacidad());
            ps.setBlob(7, sala.getImagen().getInputStream());
            ps.setString(8, empresa);
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean salaExists(String nombre) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Nombre FROM Sala "
                + "WHERE nombre = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
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

    public static Sala selectSala(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Sala WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            Sala sala = null;
            if (rs.next()) {
                sala = new Sala();
                sala.setNombre(rs.getString("Nombre"));
                sala.setDescripcion(rs.getString("Descripcion"));
                sala.setPrecio(rs.getFloat("Precio"));
                sala.setUbicacion(rs.getString("Ubicacion"));
                sala.setValoracion(valoracionMedia(id));
                sala.setId(rs.getString("id"));
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return sala;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static float valoracionMedia(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Valoracion FROM valoracion WHERE sala = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            float val = 0;
            while (rs.next()) {
                val += rs.getDouble("Valoracion");
            }
            val = val / rs.getFetchSize();
            return val;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static void getImagen(String nombre, OutputStream respuesta) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement statement = null;
            statement = connection.prepareStatement(
                    "SELECT Imagen FROM Sala WHERE id=? ");
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
    public static ArrayList<Sala> getSalas() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<Sala> salas = new ArrayList<>();
        ResultSet rs = null;
        String query = "SELECT * FROM Sala";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sala sala = new Sala();
                sala.setNombre(rs.getString("Nombre"));
                sala.setDescripcion(rs.getString("Descripcion"));
                sala.setCapacidad(rs.getInt("Capacidad"));
                sala.setCategoria(rs.getString("Categorias"));
                sala.setPrecio(rs.getFloat("Precio"));
                sala.setUbicacion(rs.getString("Ubicacion"));
                sala.setValoracion(valoracionMedia(sala.getId()));
                sala.setId(rs.getString("id"));
                salas.add(sala);
            }
            
            return salas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static int setImagen(String id, String imagen) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE Sala SET Imagen=? WHERE id = ?";
        try{   
            InputStream fin = new FileInputStream(imagen);
            ps = connection.prepareStatement(query);
            ps.setBlob(1, fin);
            ps.setString(2, id);
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        }catch(FileNotFoundException | SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static ArrayList<Sala> Filtrar(String tipo, String ubicacion, int personas, boolean b) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<Sala> salas = new ArrayList<>();
        ResultSet rs = null;
        String query = "SELECT * FROM Sala WHERE Categorias LIKE ? and Ubicacion LIKE ? and capacidad > ? and Infantil= ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%"+tipo+"%");
            ps.setString(2, "%"+ubicacion+"%");
            ps.setInt(3, personas);
            ps.setBoolean(4, b);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sala sala = new Sala();
                sala.setNombre(rs.getString("Nombre"));
                sala.setDescripcion(rs.getString("Descripcion"));
                sala.setCapacidad(rs.getInt("Capacidad"));
                sala.setCategoria(rs.getString("Categorias"));
                sala.setPrecio(rs.getFloat("Precio"));
                sala.setUbicacion(rs.getString("Ubicacion"));
                sala.setValoracion(valoracionMedia(sala.getId()));
                sala.setId(rs.getString("id"));
                salas.add(sala);
            }
            return salas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ArrayList<Sala> getSalas(String empresa) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<Sala> salas = new ArrayList<>();
        ResultSet rs = null;
        String query = "SELECT * FROM Sala WHERE Empresa=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, empresa);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sala sala = new Sala();
                sala.setNombre(rs.getString("Nombre"));
                sala.setDescripcion(rs.getString("Descripcion"));
                sala.setCapacidad(rs.getInt("Capacidad"));
                sala.setCategoria(rs.getString("Categorias"));
                sala.setPrecio(rs.getFloat("Precio"));
                sala.setUbicacion(rs.getString("Ubicacion"));
                sala.setId(rs.getString("id"));
                sala.setValoracion(valoracionMedia(sala.getId()));              
                salas.add(sala);
            }
            
            return salas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
