
package foro7poo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConexion {
    
    private static final String url = "jdbc:mysql://localhost:3306/kdelectronics";
    private static final String User = "root";
    private static final String Password = "admin";
    
    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url,User,Password);
            System.out.println("Conexion establecida");
        }catch(SQLException e){
            System.out.println("Error en la conexion");
            System.err.println(e.getMessage());
        }
        return connection;
    }
    
}
