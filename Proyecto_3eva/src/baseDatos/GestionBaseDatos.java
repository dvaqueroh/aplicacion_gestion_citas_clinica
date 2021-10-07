package baseDatos;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import clases.Cita;
import clases.Cliente;
import clases.Medico;


public class GestionBaseDatos {
	private Connection conn;
	private Conexion conexion = new Conexion();
	private Statement st; // objeto que realiza las sentencias
	private ResultSet resultado; // objeto que recibe la consulta
	private Cliente cliente;
	private Medico medico;
	private Cita cita;
	
	String id,nif,nombre,telefono;
	
	
	/* *****LOGIN USUARIO***** */
	public boolean  Login(String usuario, String pass) throws SQLException{
		
		boolean comprobar = false;
		String sql = " SELECT * FROM usuarios WHERE usuario='"+usuario+"' AND pass='"+pass+"' ";
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
				st=(Statement) conn.createStatement();
				resultado = st.executeQuery(sql);
				while(resultado.next()) {

					comprobar=true;
				}

		}
		catch(SQLException e) {
			System.out.println("prueba catch login");
			conn.close();
			e.printStackTrace(System.out);
		}
		
		return comprobar;
	}// fin metodo Comprobar Usuario
	
	
		/* ******CLIENTE******** */
	
	public boolean CompruebaCliente(String nif) throws SQLException{
		//System.out.println("metodo comprueba cliente");
		
		boolean comprobar = false;
		String sql = " SELECT * FROM cliente WHERE nifCliente = '"+nif+"' "; // sentencia busqueda cliente
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				while(resultado.next()) {

					comprobar=true;
				}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return comprobar;
	}// fin metodo Comprobar Cliente
	
	public Cliente BuscarCliente(String nif) throws SQLException {
		
		//boolean buscar = false;
		String sql = "SELECT * FROM cliente WHERE nifCliente='"+nif+"'"; // sentencia busqueda cliente
		
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				while(resultado.next()) {

					cliente = new Cliente(); // creamos objeto cliente
					cliente.setId(resultado.getString("idCliente"));
					cliente.setNif(resultado.getString("nifCliente"));
					cliente.setNombre(resultado.getString("nombreCliente"));
					cliente.setEmail(resultado.getString("emailCliente"));
					cliente.setTelefono(resultado.getString("telefonoCliente"));
					cliente.setDireccion(resultado.getString("direccionCliente"));
				}// fin while
		}//fin try
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cliente;
	}//fin busca Cliente
	
	public boolean CrearCliente(Cliente cliente) {
		//con = conexion.getConexion(); // en todos los metodos
		String id,nombre,nif,email,telefono,direccion,pass;
		boolean insertado = false;
		
		id = ""; // se pasa ID vacio para al ser campo AUTONUMERICO
		nif = cliente.getNif();
		nombre = cliente.getNombre();
		email = cliente.getEmail();
		telefono = cliente.getTelefono();
		direccion = cliente.getDireccion();
		pass = "1111"; // se inicia esta contraseña prefijada que el cliente podra cambiar en la web
		String sql = "INSERT INTO cliente(nifCliente,nombreCliente,emailCliente,telefonoCliente,direccionCliente,passCliente) values ('"+nif+"','"+nombre+ "','"+email+"','"+telefono+"','"+direccion+"','"+pass+"' )";
		try {
			conn = conexion.getConexion(); // en todos los metodos
			st = (Statement) conn.createStatement();
			if(cliente.controlDNI(nif) && cliente.controlTelefono(telefono)) {
				int confirmar = st.executeUpdate(sql);
				if(confirmar ==1) {
					insertado=true;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "NIF incorrecto, por favor, revise la numeracion y la leta");
			}
			st.close();
			conn.close();
		}// fin try
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("no creado");
		}
		return insertado;
	}// fin crear cliente
	
	public boolean BorrarCliente(String nif) { // metodo BORRAR cliente
		boolean borrar = false;
		String sql = "DELETE FROM cliente WHERE cliente.nifCliente='"+nif+"'  ";
		try {
			conn = conexion.getConexion(); // en todos los metodos
			st = (Statement) conn.createStatement();
			if(cliente.controlDNI(nif)) {
				int confirmar = st.executeUpdate(sql);
				if(confirmar ==1) {
					borrar=true;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "NIF incorrecto, por favor, revise la numeracion y la leta");
			}
			st.close();
			conn.close();
		}// fin try
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("no creado");
		}
		
		return borrar;
	}// fin metodo borrar cliente
	
	
			/* *********MEDICO********* */
	 
	/* @throws SQLException*/
	
	public ResultSet MuestraMedicos() throws SQLException {
		
		String sql = " SELECT * FROM medico "; // sentencia busqueda cliente
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
			/*	
			st.close();
				conn.close();
				*/
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}//fin muestra medicos
	
	public boolean CompruebaMedico(String nif) throws SQLException{// metodo comprueba medico
		
		boolean comprobar = false;
		String sql = " SELECT * FROM medico WHERE nifMedico = '"+nif+"' "; // sentencia busqueda Medico
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				while(resultado.next()) {

					comprobar=true;
				}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return comprobar;
	}// fin metodo Comprobar Medico
	
	public Medico BuscarMedico(String nif) throws SQLException {/// metodo busca medico
		
		String sql = "SELECT * FROM medico WHERE nifMedico='"+nif+"'"; // sentencia busqueda medico
		
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				while(resultado.next()) {
					System.out.println("nombre " + resultado.getString("nombreMedico"));
						
						medico = new Medico(); // creamos objeto Medico
						medico.setId(resultado.getString("idMedico"));
						medico.setnColegiado(resultado.getString("nColegiado"));
						medico.setNif(resultado.getString("nifMedico"));
						medico.setNombre(resultado.getString("nombreMedico"));
						medico.setTelefono(resultado.getString("telefonoMedico"));
						medico.setDireccion(resultado.getString("direccionMedico"));
						System.out.println("nombre objeto cliente "+medico.getNombre());
				}// fin while
		}//fin try
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return medico;
	}//fin busca MEDICO
	
	public boolean CrearMedico(Medico medico) { // metodo crea medico
		String id,nombre,nif,telefono,direccion,ncolegiado;
		boolean insertado = false;
		
		id = ""; // se pasa ID vacio para al ser campo AUTONUMERICO
		nif = medico.getNif();
		nombre = medico.getNombre();
		telefono = medico.getTelefono();
		direccion = medico.getDireccion();
		ncolegiado = medico.getnColegiado();
		
		String sql = "INSERT INTO medico(nColegiado,nifMedico,nombreMedico,telefonoMedico,direccionMedico) values ('"+ncolegiado+"','"+nif+"','"+nombre+ "','"+telefono+"','"+direccion+"' )";
		try {
			conn = conexion.getConexion(); // en todos los metodos
			st = (Statement) conn.createStatement();
			if(medico.controlDNI(nif) && medico.controlTelefono(telefono) && medico.controlNumColegiado(ncolegiado)) {
				int confirmar = st.executeUpdate(sql);
				if(confirmar ==1) {
					insertado=true;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "NIF incorrecto, por favor, revise la numeracion y la leta");
			}
			st.close();
			conn.close();
		}// fin try
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("no creado");
		}
		return insertado;
	}// fin boton crear medico
	
	public boolean BorrarMedico(String nif) { // metodo BORRAR medico
		boolean borrar = false;
		String sql = "DELETE FROM medico WHERE medico.nifMedico='"+nif+"'  ";
		try {
			conn = conexion.getConexion(); // en todos los metodos
			st = (Statement) conn.createStatement();
			if(medico.controlDNI(nif)) {
				int confirmar = st.executeUpdate(sql);
				if(confirmar ==1) {
					borrar=true;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "NIF incorrecto, por favor, revise la numeracion y la leta");
			}
			st.close();
			conn.close();
		}// fin try
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("no creado");
		}
		
		return borrar;
	}// fin metodo borrar Medico
	
		/* ***********CITAS************* */

	public ResultSet MostrarCitas() throws SQLException { // muestra TODAS las citas
		
		String sql = "SELECT cita.idCita,cita.fechaCita,cita.estadoCita,cliente.nombreCliente,cliente.nifCliente,medico.nombreMedico,sala.idSala,horas.Hora FROM cita INNER JOIN cliente ON cita.idClienteAux = cliente.idCliente INNER JOIN medico ON cita.idMedicoAux = medico.idMedico INNER JOIN sala ON  cita.idSalaAux = sala.idSala INNER JOIN horas ON cita.idHoraAux = horas.idHora ORDER BY fechaCita ASC , Hora ASC"; // sentencia busqueda CITA
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				//st.close();
				//con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}// fin mostrar CITAS
	
	public ResultSet MostrarCitasHoy() throws SQLException { // muestra TODAS las citas de HOY
		
		String sql = "SELECT cita.idCita,cita.fechaCita,cita.estadoCita,cliente.nombreCliente,cliente.nifCliente,medico.nombreMedico,sala.idSala,horas.Hora FROM cita INNER JOIN cliente ON cita.idClienteAux = cliente.idCliente INNER JOIN medico ON cita.idMedicoAux = medico.idMedico INNER JOIN sala ON  cita.idSalaAux = sala.idSala INNER JOIN horas ON cita.idHoraAux = horas.idHora WHERE DATE(fechaCita) = DATE(NOW()) ORDER BY fechaCita ASC , Hora ASC"; // sentencia busqueda CITA
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				//st.close();
				//con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}// fin mostrar CITAS HOY
	
	public ResultSet MostrarCitasClienteHoy(String nif) throws SQLException { // muestra TODAS las citas de HOY
		
		String sql = "SELECT cita.idCita,cita.fechaCita,cita.estadoCita,cliente.nombreCliente,cliente.nifCliente,medico.nombreMedico,sala.idSala,sala.tamanoSala, horas.Hora FROM cita INNER JOIN cliente ON cita.idClienteAux = cliente.idCliente INNER JOIN medico ON cita.idMedicoAux = medico.idMedico INNER JOIN sala ON  cita.idSalaAux = sala.idSala INNER JOIN horas ON cita.idHoraAux = horas.idHora WHERE DATE(fechaCita) >= DATE(NOW()) AND cliente.nifCliente = '"+nif+"' ORDER BY fechaCita ASC , Hora ASC"; // sentencia busqueda CITA de cliente Hoy
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				//st.close();
				//con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}// fin mostrar CITAS HOY
	
	public ResultSet MostrarCitaCliente(String nif) throws SQLException {
		
		String sql = "SELECT cita.idCita, cita.fechaCita, cita.estadoCita,horas.Hora,sala.idSala, sala.tamanoSala,medico.nombreMedico FROM(cita INNER JOIN cliente on cita.idClienteAux = cliente.idCliente INNER JOIN medico ON cita.idMedicoAux = medico.idMedico INNER JOIN sala ON  cita.idSalaAux = sala.idSala INNER JOIN horas ON cita.idHoraAux = horas.idHora)  WHERE cliente.nifCliente like'"+nif+"' ORDER BY fechaCita ASC , Hora ASC";
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				//st.close();
				//con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}// fin mostrar CITAS.
	
	public ResultSet MostrarCitaSala(String sala) throws SQLException {
		
		String sql = "SELECT cita.idCita, cita.fechaCita, cita.idHoraAux, cita.idSalaAux, cita.idMedicoAux, cita.estadoCita FROM cita where cita.idSalaAux like'"+sala+"' ORDER BY fechaCita ASC , Hora ASC";
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				//st.close();
				//con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}// fin mostrar CITAS
	
	public ResultSet MostrarCitaFechaSala(String fecha,String sala) throws SQLException {
		
		String sql = "SELECT cita.idCita, cita.fechaCita,horas.Hora,sala.idSala FROM(cita INNER JOIN sala ON  cita.idSalaAux = sala.idSala INNER JOIN horas ON cita.idHoraAux = horas.idHora) WHERE cita.fechaCita='"+fecha+"' AND cita.idSalaAux='"+sala+"' ";
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				//st.close();
				//conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}// fin mostrar CITAS
	
	
	public boolean CrearCita(Cita cita) throws SQLException {
		String idCita,idClienteAux,idMedicoAux,idSalaAux,fechaCita,idHoraAux,estadoCita;
		boolean insertado = false;
		idClienteAux = cita.getIdClienteAux();
		idMedicoAux = cita.getIdMedicoAux();
		idSalaAux = cita.getIdSalaAux();
		fechaCita = cita.getFechaCita();
		idHoraAux = cita.getIdHoraAux();
		estadoCita = cita.getEstadoCita();
	
		String sql = "INSERT INTO cita(idClienteAux,idMedicoAux,idSalaAux,fechaCita,idHoraAux,estadoCita) values ('"+idClienteAux+"','"+idMedicoAux+ "','"+idSalaAux+"','"+fechaCita+"','"+idHoraAux+"','"+estadoCita+"' )";
		try {
			conn = conexion.getConexion(); // en todos los metodos
			st = (Statement) conn.createStatement();
				int confirmar = st.executeUpdate(sql);
				if(confirmar ==1) {
					insertado=true;
			}
			else {
				
			}
			st.close();
			conn.close();
		}// fin try
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("no creado");
		}
		return insertado;
	}// fin CREA CITA
	
	public boolean AnularCita(String idCita){
		boolean anular=false;
		String sql = "UPDATE cita SET cita.estadoCita='2' WHERE idCita='"+idCita+"' ";
		try {
			conn = conexion.getConexion();
			st = (Statement) conn.createStatement();
			int confirmar = st.executeUpdate(sql);
			if(confirmar ==1) {
				anular=true;
			}
			else {
				
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // en todos los metodos
		
		return anular;
	}// fin Anular Cita
	
	public boolean ActualizarCita() {
		boolean actualizar=false;
		String sql = "UPDATE cita SET cita.estadoCita='0' WHERE DATE(fechaCita) = DATE(NOW()) ";
		try {
			conn = conexion.getConexion();
			st = (Statement) conn.createStatement();
			int confirmar = st.executeUpdate(sql);
			if(confirmar ==1) {
				System.out.println("citas actualizadas");
				actualizar=true;
			}
			else {
				
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // en todos los metodos
		
		return actualizar;
	}// fin Actualizar citas
	
			/*  *****SALAS*****  */
	
	public ResultSet MostrarSalas() throws SQLException {// devuelve un String con todas las salas
		
		String sql = "SELECT * FROM sala"; // sentencia busqueda SALA
		
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				//st.close();
				//con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}// fin Mostrar SALAS
	
	/* HORAS */
	
	
	public ResultSet MostrarHoras() throws SQLException {
		
		String sql = "SELECT * FROM horas"; // sentencia busqueda SALA
		
		try {
			conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
				//st.close();
				//con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}// fin mostrar Horas
	

	public ResultSet devuelverHora(String hora) throws SQLException {
		conn = conexion.getConexion(); // nueva conexion a la bbdd CREARLO EN TODOS LOS METODOS
		String sql = " SELECT * FROM horas WHERE idHora = '"+hora+"' "; // sentencia busqueda HORA
			st=(Statement) conn.createStatement();
			resultado = st.executeQuery(sql);
			System.out.println("devuelve la hora pasada");
		return resultado;
	}// fin mostrar Horas



}// fin Clase GESTION BASE DATOS
