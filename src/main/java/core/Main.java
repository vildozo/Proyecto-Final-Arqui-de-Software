package core;
import static spark.Spark.*;

public class Main {
	public static void main(String[] args) {
		get("/", (request, response) -> hola());
		post("/hola", (request, response) -> responder_saludo(request.queryParams("nombre_saludo")));
		get("/Arquitectura", (request, response) -> "Hola Arquitectura");
		get("/register_employee", (request, response) -> empleado());
		post("/registered_employee", (request, response) -> responder_registro(request.queryParams("employee_Name"),request.queryParams("address") ));
	}

	private static String responder_saludo(String nombre){
		System.out.println("----------RESPONDIENDO---------");
		return "Hola "+nombre;
	}
	
	private static String responder_registro(String nombre,String address){
		System.out.println("----------RESPONDIENDO---------");
		return "El nuevo empleado es "+nombre+" y su direccion es "+ address;
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
	
	private static String empleado() {
		return "<html>"
				+ "<body>"
				+ "<form method='post' action='/registered_employee'>" 
				+ "<label>Name:</label>"
				+ "<input type='text' name='employee_Name'>"
				+ "<br>"
				+ "<label>Address:</label>"
				+ "<input type='text' name='address'>"
				+ "<br>"
				+ "<input type='submit' value='Create employee'"
				+ "</body>"
				+ "</html>";
		
	}
	
	 
}
