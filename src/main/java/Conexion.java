import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexion {

	// Atributos
	private Connection conexion;
	private String url;
	private String usuario;
	private String password;
	
	// Constructores
	public Conexion(String url, String usuario, String password) {
		
		this.url = url;
		this.usuario = usuario;
		this.password = password;
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
            JOptionPane.showMessageDialog(null, "Conexión al servidor MySQL exitosa.");
            
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "No se ha podido conectar con el servidor MySQL.");
            System.out.println(e);
        }
	}

	// Métodos
	public Connection getConexion() {
		return conexion;
	}

	public String getUrl() {
		return url;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}
	
	// Cierra la conexión con MySQL
	public void cerrarConexion() {
		try {
            conexion.close();
            JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el servidor MySQL.");
            
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	// Crea una BD
	public void crearDB(String name) {
        try {
            String query = "CREATE DATABASE " + name;
            Statement st = conexion.createStatement();
            st.execute(query);

            JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + name + " de forma exitosa.");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	
	// Crea una tabla
	public void crearTabla(String db, String query) {
        try {
            String queryDb = "USE " + db + ";";
            Statement stdb = conexion.createStatement();
            stdb.executeUpdate(queryDb);

            Statement st = conexion.createStatement();
            st.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Tabla creada con éxito.");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	
	// Inserta datos en una tabla
	public void insertarDatos(String db, String query) {
		try {
			String queryDb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(queryDb);
			
			Statement st = conexion.createStatement();
            st.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente.");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}