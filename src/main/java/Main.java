
import static spark.Spark.*;

import Presenter.Presentador_empleado;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.boundaries.PayrollDatabase;


public class Main {
	public static void main(String[] args) {
		PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
		//Presentador_empleado = 
		
		/*revisar las transacciones addTransactions, la transacciones se ejecuten despues
		-VELOCITY
		-tell don't ask
		-MODEL VIEW PRESENTER(una clase que se encargue de hacer la logica de html con empleado)
		-se puede utilizar un decorador de empleado
		-la vista se comunica con el presentador de empleado que hace un wrap 
		-    alrededor de empleado
		-donde esta se encarga en convertir a cadena los datos de empleado para 
		-que muestre en html
		--PRESENTER AS A DECORATOR
		 * enpayrolldatabase hacer una funcion que devuela los objetos empleado
		 */
		
		/*ui aplicando clean code y solid
		 * crear empleaod
		 * mostrar lista de empleados un boton generar otro agregar
		 * generar el pago segun tipo de empleado
		 */
		
		
		
		
		
		
		
		
		
		
		
		get("/", (request, response) -> hola());
		post("/hola", (request, response) -> responder_saludo(request.queryParams("nombre_saludo")));
		
		get("/Arquitectura", (request, response) -> "Hola Arquitectura");
		
		get("/register_employee", (request, response) -> Presentador_empleado.empleado());
		post("/registered_employee", (request, response) -> Presentador_empleado.responder_registro(request.queryParams("employee_CI"),request.queryParams("employee_Name"),request.queryParams("address") ));
		
		//get("/Show_employees", (request, response) -> MostrarEmpleados();
			
	}

	private static String responder_saludo(String nombre){
		System.out.println("----------RESPONDIENDO---------");
		return "Hola "+nombre;
	}
	
	

	private static String hola() {
		return "<html>"
				+ "<body>"
				+ "<form method='post' action='/hola'>" 
				+ "<label>Nombre:</label>"
				+ "<input type='text' name='nombre_saludo'>"
				+ "<input type='submit' value='Saluda'"
				+ "</body>"
				+ "</html>";
		
	}
	
	
	private static String MostrarEmpleados(){
		//queryParams("employee_CI"),request.queryParams("employee_Name"),request.queryParams("address")
		return "<html>"
				+ "<body>"
				
				+ "</body>"
				+ "</html>";
		
	
	}
	
	 
}
