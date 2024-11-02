
package foro7poo.main;

import foro7poo.dao.ProductoDao;
import foro7poo.model.Producto;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) throws SQLException {
        ProductoDao productoDao = new ProductoDao();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Ver Productos");
            System.out.println("3. Consultar Por Producto");
            System.out.println("4. Actualizar Producto");
            System.out.println("5. Eliminar Producto");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
switch (opcion) {
                case 1:
                    System.out.print("Código del producto: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Precio Base: ");
                    double precioBase = scanner.nextDouble();
                    System.out.print("Precio Venta: ");
double precioVenta = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.print("Categoría: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Cantidad Disponible: ");
                    int cantidad = scanner.nextInt();

                    Producto nuevoProducto = new Producto(codigo, nombre, descripcion, precioBase, precioVenta, categoria, cantidad);
                    productoDao.agregarProducto(nuevoProducto);
                    System.out.println("Producto agregado exitosamente.");
                    break;
case 2:
                    List<Producto> productos = productoDao.obtenerProductos();
                    System.out.println("Lista de Productos:");
                    for (Producto producto : productos) {
                        System.out.println("Código: " + producto.getCodigoProducto() +
                                           ", Nombre: " + producto.getNombre() +
                                           ", Descripción: " + producto.getDescripcion() +
                                           ", Precio Base: " + producto.getPrecioBase() +
                                           ", Precio Venta: " + producto.getPrecioVenta() +
                                           ", Categoría: " + producto.getCategoria() +
                                           ", Cantidad Disponible: " + producto.getCantidadDisponible());
                    }
case 3:
    
                   System.out.println("Codigo del producto por consultar");
                   codigo = scanner.nextInt();
                   Producto producto = productoDao.obtenerProductoPorId(codigo);
                   if(producto!=null){
                       
                       System.out.println("Nombre: " + producto.getNombre());
                       System.out.println("Descripción: " + producto.getDescripcion() );
                       System.out.println("Precio Base: " + producto.getPrecioBase() );
                       System.out.println("Precio Venta: " + producto.getPrecioVenta() );
                       System.out.println("Categoría: " + producto.getCategoria() );
                       System.out.println("Cantidad Disponible: " + producto.getCantidadDisponible());
                   }else{
                       System.out.println("Producto no encontrado");
                   }
case 4: 
                   System.out.println("Codigo del producto a actualzar");
                   codigo = scanner.nextInt();
                   scanner.nextLine();
                   
                  
                    System.out.print("Nuevo Nombre: ");
                    nombre = scanner.nextLine();
                    System.out.print("Nueva Descripción: ");
                    descripcion = scanner.nextLine();
                    System.out.print("Nuevo Precio Base: ");
                    precioBase = scanner.nextDouble();
                    System.out.print("Nuevo Precio Venta: ");
                    precioVenta = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.print("Nueva Categoría: ");
                    categoria = scanner.nextLine();
                    System.out.print("Nueva Cantidad Disponible: ");
                    cantidad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    
                    Producto productoActualizado = new Producto(codigo, nombre, descripcion, precioBase, precioVenta, categoria, cantidad);
                    productoDao.actualizarProducto(productoActualizado);
                    System.out.println("Actualizacion exitosa");
                    break;
case 5:
    
                    System.out.println("Codigo del producto a eliminar");
                    codigo = scanner.nextInt();
                    productoDao.eliminarProducto(codigo);
                    System.out.println("Producto eliminado");
                    break;
                    
    
case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
}
    }
    
}
