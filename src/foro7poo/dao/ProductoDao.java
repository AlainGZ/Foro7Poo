
package foro7poo.dao;

import foro7poo.db.DataBaseConexion;
import foro7poo.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    public void agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (codigoProducto, nombre, descripcion, precioBase, precioVenta, categoria, cantidadDisponible) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn != null) {
pstmt.setInt(1, producto.getCodigoProducto());
                pstmt.setString(2, producto.getNombre());
                pstmt.setString(3, producto.getDescripcion());
                pstmt.setDouble(4, producto.getPrecioBase());
                pstmt.setDouble(5, producto.getPrecioVenta());
                pstmt.setString(6, producto.getCategoria());
                pstmt.setInt(7, producto.getCantidadDisponible());
                pstmt.executeUpdate();
                System.out.println("Producto agregado exitosamente.");
            } else {
                System.out.println("No se pudo agregar el producto debido a un problema de conexión.");
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar el producto:");
            System.err.println(e.getMessage());
        }
    }
public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection conn = DataBaseConexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (conn != null) {
while (rs.next()) {
                    Producto producto = new Producto(
                        rs.getInt("codigoProducto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precioBase"),
                        rs.getDouble("precioVenta"),
                        rs.getString("categoria"),
                        rs.getInt("cantidadDisponible")
                    );
                    productos.add(producto);
                }
            } else {
                System.out.println("No se pudo obtener la lista de productos debido a un problema de conexión.");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los productos:");
            System.err.println(e.getMessage());
        }
        return productos;
    }

 public Producto obtenerProductoPorId(int codigoProducto) throws SQLException {
        String query = "SELECT * FROM Productos WHERE codigoProducto = ?";
        Producto producto = null;
        try (Connection conn = DataBaseConexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            if(conn!=null){
            pstmt.setInt(1, codigoProducto);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto(
                        rs.getInt("codigoProducto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precioBase"),
                        rs.getDouble("precioVenta"),
                        rs.getString("categoria"),
                        rs.getInt("cantidadDisponible")
                    );
                }else{
                    System.out.println("No se encontro un producto con ese codigo");
                }
            }
            }else{
             System.out.println("No se pudo obtener el producto. error de conexion");   
              }
        }catch (SQLException e){
            System.out.println("Error al obtener el producto");
            System.err.println(e.getMessage());
        }
        return producto;
    }

 public void actualizarProducto(Producto producto) throws SQLException {
        String sql = "UPDATE Productos SET  nombre = ?, descripcion = ?, precioBase = ?, precioVenta = ?, categoria = ?, cantidadDisponible = ? where codigoProducto = ?";
        try (Connection conn = DataBaseConexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if(conn!= null){
              
                pstmt.setString(1, producto.getNombre());
                pstmt.setString(2, producto.getDescripcion());
                pstmt.setDouble(3, producto.getPrecioBase());
                pstmt.setDouble(4, producto.getPrecioVenta());
                pstmt.setString(5, producto.getCategoria());
                pstmt.setInt(6, producto.getCantidadDisponible());
                pstmt.setInt(7, producto.getCodigoProducto());
                pstmt.executeUpdate();
                
                int rowsUpdated = pstmt.executeUpdate();
               if(rowsUpdated > 0){
                    System.out.println("Producto actualizado");
                }else{
                    System.out.println("No se encontro un producto con el codigo que digito");
                }
            }else{
                System.out.println("No se pudo actualizar el producto. Problema de conexion");
            }   
        }catch(SQLException e){
            System.out.println("Error al actualizar el registro");
            System.err.println(e.getMessage());
        }
        return;
    }

    // Eliminar producto
    public void eliminarProducto(int codigoProducto){
        String sql = "UPDATE productos SET cantidadDisponible = 0 WHERE codigoProducto = ?";
        try(Connection conn = DataBaseConexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            if(conn!=null){
                pstmt.setInt(1, codigoProducto);
                int rowsUpdated = pstmt.executeUpdate();
                if(rowsUpdated > 0){
                    System.out.println("Producto eliminado");
                }else{
                    System.out.println("No se encontro un producto con el codigo que digito");
                }
            }else{
                System.out.println("No se pudo eliminar el producto. Problema de conexion");
            }
            
        }catch(SQLException e){
            System.out.println("Error al eliminar el registro");
            System.err.println(e.getMessage());
        }
        return;
    }
}
