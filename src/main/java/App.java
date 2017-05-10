import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import Presenter.Presentador_empleado;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


public class App {
	public static void main(String[] args) {
		HashMap<String, Object> model = new HashMap<String, Object>();
		
		get("/", (request, response) -> hola());
		
		get("/register_employee", (request, response) -> {
			return new ModelAndView(model, "templates/register_employee.vtl");
		}, new VelocityTemplateEngine());
		
		post("/create_employee", (request, response) -> {
			Presentador_empleado.createEmployee(request.queryParams("employeeId"),
					request.queryParams("name"),request.queryParams("address"),
					request.queryParams("paymentClassification"),request.queryParams("amount"),
					request.queryParams("commission"));
			response.redirect("/");
			return null;
		});
		
		get("/search_employee", (request, response) -> {
			return new ModelAndView(model, "templates/search_employee.vtl");
		}, new VelocityTemplateEngine());
		
		post("/show_employee", (request, response) -> {
			Employee employee = PayrollDatabase.globalPayrollDatabase.getEmployee(Integer.parseInt(request.queryParams("employeeId")));
			model.put("employee", employee);
			return new ModelAndView(model, "templates/show_employee.vtl");
		}, new VelocityTemplateEngine());
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
