import static spark.Spark.*;

import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


public class App {
	public static void main(String[] args) {
		get("/", (request, response) -> hola());
		
		get("/register_employee", (request, response) -> {
			return new ModelAndView(new HashMap(), "templates/register_employee.vtl");
		}, new VelocityTemplateEngine());
		
//		post("/registered_employee", (request, response) -> Presentador_empleado.responder_registro(request.queryParams("employee_CI"),request.queryParams("employee_Name"),request.queryParams("address"),request.queryParams("payment"),request.queryParams("amount") ));
		
		//get("/Show_employees", (request, response) -> MostrarEmpleados();
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
