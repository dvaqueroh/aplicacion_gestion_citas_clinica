package baseDatos;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import graficos.VentanaPrincipal;
	
public class Conexion {
	private Connection conn; // crear objeto conecttion
	private Statement st; // objeto que realiza las sentencias
	public Connection getConexion() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver"); // driver tipo base datos
			String url = "jdbc:mysql://localhost/clinica"; // direccion de la base de datos
			String usuario = "root"; // usuario de la bbdd
			String contrasenia = ""; // pass bbdd
			
			conn = (Connection) DriverManager.getConnection(url, usuario, contrasenia);

		}
		catch(ClassNotFoundException e) {
			System.out.println("No se encuentra el controlador");
			JOptionPane.showMessageDialog(null, "No se encuentra el controlador");
			//VentanaPrincipal frame = new VentanaPrincipal();
			//e.getStackTrace();
			e.printStackTrace(System.out);

		}
		catch(SQLException e) {
			
			System.out.println("Error en la conexion");
			JOptionPane.showMessageDialog(null, "Error en la conexion");
			System.out.println("Código de Error: " + e.getErrorCode() + "\n" + "SLQState: " + e.getSQLState() + "\n" + "Mensaje: " + e.getMessage() + "\n");
			Throwable t = e.getCause();
			while(t != null) {
			  System.out.println("Causa: " + t + "\n");
			  t = t.getCause();
			}
	            System.exit( 1 );
			e.printStackTrace();
		}

		return conn;
	}// connection
	
	
}// fin Clase Conexion
