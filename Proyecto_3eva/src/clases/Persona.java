package clases;

import javax.swing.JOptionPane;

public class Persona {
	
	String id,nif,nombre,email,telefono,direccion;
	
	

	public Persona() {

	}

	public Persona(String id, String nif,String nombre, String email,String telefono, String direccion) {
	
		this.id = id;
		this.nif = nif;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public static boolean controlDNI(String dni) {
		boolean controldni=false;
        boolean comprobarLetra;
        boolean comprobarNumero;
        int letra = 0;
        int contaNumeros = 0;
        String cadNumeros = "";
        int numeros;
        int resto;
        char[] arr = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
      	
        
        dni = dni.toUpperCase();
        if(dni.matches(".*[0-9,a-z,A-Z].*")==true && dni.length()==9) {        
	        comprobarLetra = Character.isLetter(dni.charAt(8));
	        if(comprobarLetra == false) {
	            controldni=false;
	        } 
	        else {
	            letra = dni.charAt(8);
	            if(letra >= 'A' && letra <='Z') {
	                //letra = letra -32;
	                cadNumeros = dni.substring(0,8);
	            }
	            for(int i = 0;i <= 7;i++){
	                comprobarNumero = Character.isDigit(dni.charAt(i));
	                if(comprobarNumero == true) {
	                    contaNumeros++;
	                } else {
	                    controldni=false;
	                }
	            }
	            if(contaNumeros == 8) {
	                numeros = Integer.valueOf(cadNumeros);
	                resto = numeros%23;
	                if(arr[resto] == letra) {
	                	controldni=true;
	                } else {
	                	JOptionPane.showMessageDialog(null, "NIF erroneo, la letra deberia ser:" + arr[resto]);
	                	System.out.println("NIF erroneo, la letra deberia ser:" + arr[resto]);
	                    controldni=false;
	                }//fin if LETRA
	            }// fin if numeros
	        }  
        }//FIN if comprobacion numeros letras
        else {
			controldni=false;
        }// fin else dni control
        return controldni;
	}//Fin metodo DNI
	
	public static boolean controlTelefono(String tlf) {

		boolean controlmovil=false;

		if (tlf.matches(".*[0-9].*")==true && tlf.length()==9){
				//System.out.println("el telefono: " +tlf +" es correcto");
				controlmovil=true;
			}//fin if telefono 9 cifras
			else {
				//System.out.println("el telefono: " +tlf +" es Incorrecto, debe tener 9 cifras");
				JOptionPane.showMessageDialog(null, "El telefono debe tener 9 cifras");
				controlmovil=false;
			}//fin else telefono
		return controlmovil;	
}// fin metodo control Telefono
	

}
