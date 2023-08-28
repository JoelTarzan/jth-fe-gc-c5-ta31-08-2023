import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Ejercicio01App {

	public static void main(String[] args) {

		// Creamos la conexion
		Conexion conexion = new Conexion("jdbc:mysql://localhost:3306?useTimezone=true&serverTimezone=UTC", "root", "root");

		// Creamos la BD
		conexion.crearDB("tiendainformatica");

		// Creamos la query de la primera tabla y la tabla
		String queryFabricantes = "CREATE TABLE fabricantes "
				+ "(codigo int AUTO_INCREMENT, nombre varchar(100),"
				+ "PRIMARY KEY(codigo));";
		conexion.crearTabla("tiendainformatica", queryFabricantes);

		// Creamos la query de la segunda tabla y la tabla
		String queryArticulos = "CREATE TABLE articulos "
				+ "(codigo int AUTO_INCREMENT, nombre varchar(100), precio int, fabricante int,"
				+ "PRIMARY KEY(codigo), FOREIGN KEY(fabricante) REFERENCES fabricantes (codigo) ON DELETE cascade ON UPDATE cascade);";
		conexion.crearTabla("tiendainformatica", queryArticulos);
		
		// Creamos ahora las query para insertar datos en las dos tablas
		String queryRegistrosFabricantes = "INSERT INTO fabricantes (nombre) VALUES "
				+ "('Apple'), ('Tesla'), ('LG'), ('Toyota'), ('Nike');";
		conexion.insertarDatos("tiendainformatica", queryRegistrosFabricantes);
		
		String queryRegistrosArticulos = "INSERT INTO articulos (nombre, precio, fabricante) VALUES "
				+ "('iPhone 14 Pro', 1199, 1), ('Tesla Model 3', 50000, 2), ('Pantalla OLED 144Hz', 350, 3), ('Yaris', 8000, 4), ('Camiseta KSJ', 35, 5);";
		conexion.insertarDatos("tiendainformatica", queryRegistrosArticulos);

		// Cerramos la conexión
		conexion.cerrarConexion();
	}
}