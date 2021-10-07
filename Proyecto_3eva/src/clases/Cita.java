package clases;

public class Cita {
	String idCita,idClienteAux,idMedicoAux,idSalaAux,fechaCita,idHoraAux,estadoCita;
	

	public Cita() {
	}


	protected Cita(String idCita, String idClienteAux, String idMedicoAux, String idSalaAux, String fechaCita,
			String idHoraAux, String estadoCita) {
		
		this.idCita = idCita;
		this.idClienteAux = idClienteAux;
		this.idMedicoAux = idMedicoAux;
		this.idSalaAux = idSalaAux;
		this.fechaCita = fechaCita;
		this.idHoraAux = idHoraAux;
		this.estadoCita = estadoCita;
	}

	/* GET SET*/
	public String getIdCita() {
		return idCita;
	}


	public void setIdCita(String idCita) {
		this.idCita = idCita;
	}


	public String getIdClienteAux() {
		return idClienteAux;
	}


	public void setIdClienteAux(String idClienteAux) {
		this.idClienteAux = idClienteAux;
	}


	public String getIdMedicoAux() {
		return idMedicoAux;
	}


	public void setIdMedicoAux(String idMedicoAux) {
		this.idMedicoAux = idMedicoAux;
	}


	public String getIdSalaAux() {
		return idSalaAux;
	}


	public void setIdSalaAux(String idSalaAux) {
		this.idSalaAux = idSalaAux;
	}


	public String getFechaCita() {
		return fechaCita;
	}


	public void setFechaCita(String fechaCita) {
		this.fechaCita = fechaCita;
	}


	public String getIdHoraAux() {
		return idHoraAux;
	}


	public void setIdHoraAux(String idHoraAux) {
		this.idHoraAux = idHoraAux;
	}


	public String getEstadoCita() {
		return estadoCita;
	}


	public void setEstadoCita(String estadoCita) {
		this.estadoCita = estadoCita;
	}
	
	
	

}// fin clase Cita
