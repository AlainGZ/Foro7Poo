
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
}
