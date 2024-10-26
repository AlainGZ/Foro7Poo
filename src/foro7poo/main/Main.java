
package foro7poo.main;

import foro7poo.dao.ProductoDao;
import foro7poo.model.Producto;
import java.util.List;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        ProductoDao productoDao = new ProductoDao();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Ver Productos");
            System.out.println("3. Salir");
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
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
}
    }
    
}
